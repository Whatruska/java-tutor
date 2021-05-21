package Control;

import Control.annotations.Bean;
import Control.annotations.FieldName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper implements RepositoryFileHelper {
    //{класс -> имя_файла_в_бд}
    private HashMap<Class, String> classFilenameMap = new HashMap<>();

    //id + бин
    private class WithId {
        private String id;
        private Object object;

        public WithId(String id, Object object) {
            this.id = id;
            this.object = object;
        }
    }

    //Возвращает список классов в директории classes с аннотацией @Bean
    private List<Class> getBeanableClasses () {
        //Директория классов
        File folder = new File(Paths.get(Settings.CLASSES_FOLDER_PATH).toUri());
        File[] files = folder.listFiles();

        //Java-файлы внутри нее
        List<File> javaFiles = Stream.of(files).filter(file -> file.getName().contains(".java")).collect(Collectors.toList());

        //Получаем названия java-классов по именам файлов
        List<String> classNames = javaFiles.stream().map(file -> {
            String rawName = file.getName();
            String[] parts = rawName.split("\\.");
            return Settings.PACKAGE_PREFIX + "." + parts[0];
        }).collect(Collectors.toList());

        //Получаем настоящие java-классы
        List<Class> classes = classNames.stream().map(className -> {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        //Оставляем только те, у которых есть @Bean
        return classes.stream().filter(c -> c.isAnnotationPresent(Bean.class)).collect(Collectors.toList());
    }

    //Приводит строчку к нужному классу
    private Object castValueToClass (Class c, String value) {
        if (value.equals("null")) return null;

        if (c == Integer.class || c == int.class) {
            return Integer.parseInt(value);
        } else if (c == Double.class || c == double.class) {
            return Double.parseDouble(value);
        }

        return value;
    }

    //Возвращает сущность {id -> бин}
    private WithId parseObjectFromPropsMap (Class c, Map<String, String> props) {
        try {
            //Создаем пустой объект класса
            Object obj = c.newInstance();
            //Вытаскиваем id из карты свойств
            String id = props.get(Settings.ID_KEY);

            Field[] fields = c.getDeclaredFields();
            for (Field field: fields) {
                //Делаем поля public
                field.setAccessible(true);
                FieldName fieldName = field.getDeclaredAnnotation(FieldName.class);

                //Получаем ключ мапы, где хранится нужное нам свойство (обрабатываем аннотацию FieldName)
                String propName = fieldName == null ? field.getName() : fieldName.name();
                String value = props.get(propName);

                //Приводим значение к объекту класса поля (кастуем строчку к классу поля)
                Object castedValue = castValueToClass(field.getType(), value);

                //Записываем в объект приведенное значение
                field.set(obj, castedValue);
            }

            return new WithId(id, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //Загружает бины для конкретного класса
    private Map<String, Object> loadBeansForClass (Class c) {
        //Бины в формате {id -> бин}
        Map<String, Object> beans = new HashMap<>();

        Bean bean = (Bean) c.getDeclaredAnnotation(Bean.class);

        //Получаем имя файла, в котором хранятся бины класса (если в @Bean это указано - учитываем)
        String fileName = Settings.DB_FOLDER_PATH + "/" + (bean.fileName().equals("") ? c.getSimpleName() : bean.fileName()) + ".txt";

        //Запоминаем имя файла для сохранения
        classFilenameMap.put(c, fileName);

        File file = new File(Paths.get(fileName).toUri());
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                //Карта свойств объекта
                Map<String, String> props = new HashMap<>();

                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    //Если дошли до разделителя сущностей, парсим объект на основе мапы свойств + обнуляем ее
                    if (line.equals(Settings.ENTITY_SEPARATOR)) {
                        WithId entity = parseObjectFromPropsMap(c, props);
                        beans.put(entity.id, entity.object);
                        props = new HashMap<>();
                    //Если нет - добавляем пару ключ-значение в мапу свойств
                    } else {
                        String[] parts = line.split(Settings.KEY_VALUE_SEPARATOR);
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        props.put(key, value);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return beans;
    }

    @Override
    //Загружает все бины
    public Map<String, Object> loadBeans() {
        Map<String, Object> beans = new HashMap<>();
        List<Class> classes = getBeanableClasses();
        classes.forEach(cl -> {
            beans.putAll(loadBeansForClass(cl));
        });
        return beans;
    }

    //Сохранить в БД список бинов конкретного класса
    private void saveEntitiesForClass(Class c, List<WithId> entities) {
        //Получаем из мапы имя файла для класса
        String fileName = classFilenameMap.get(c);
        File file = new File(Paths.get(fileName).toUri());
        try {
            PrintWriter writer = new PrintWriter(file);

            //Записываем каждый бин
            entities.forEach(entity -> {
                String id = entity.id;
                Object obj = entity.object;

                StringBuilder builder = new StringBuilder();

                //Записываем id и класс
                builder.append(Settings.ID_KEY + " ").append(Settings.KEY_VALUE_SEPARATOR + " ").append(id).append("\n");
                builder.append(Settings.CLASS_KEY + " ").append(Settings.KEY_VALUE_SEPARATOR + " ").append(c.getName()).append("\n");

                //Проходимся по полям и записываем конкретные значения
                Field[] fields = c.getDeclaredFields();
                for (Field field: fields) {
                    field.setAccessible(true);
                    FieldName fieldName = field.getDeclaredAnnotation(FieldName.class);

                    //Смотрим как именно записать это поле в файл (обрабатываем аннотацию)
                    String trueFieldName = fieldName == null ? field.getName() : fieldName.name();
                    try {
                        //Записываем поле и его значение (field.get(obj) - получение значения поля конкретного бина)
                        builder.append(trueFieldName).append(" ").append(Settings.KEY_VALUE_SEPARATOR + " ").append(field.get(obj)).append("\n");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                //Добавляем разделитель сущностей
                builder.append(Settings.ENTITY_SEPARATOR).append("\n");

                //Записываем все в файлик
                writer.print(builder);
            });

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveEntities(Map<String, Object> entries) {
        //{класс -> список_бинов_класса_с_id}
        Map<Class, List<WithId>> classObjsMap = new HashMap<>();

        //Перегруппировываем мапу
        entries.keySet().forEach(key -> {
            //Берем бин
            Object entity = entries.get(key);

            //Смотрим его класс
            Class c = entity.getClass();

            //Смотрим, какие бины уже записаны для этого класса
            List<WithId> beans = classObjsMap.get(c);

            //Если список пустой - создаем
            if (beans == null) {
                beans = new ArrayList<>();
            }

            //Добавляем все в новую мапу
            beans.add(new WithId(key, entity));
            classObjsMap.put(c, beans);
        });

        //Отправляем мапу на запись поклассово
        classObjsMap.keySet().forEach(key -> saveEntitiesForClass(key, classObjsMap.get(key)));
    }
}
