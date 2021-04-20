package Lesson_9.bot;

import java.lang.reflect.Method;
import java.util.stream.Stream;

public class Bot {
    @Command(
            name = "Привет",
            alias = {
                    "хай", "здарова", "приветики"
            },
            description = "Поздороваться с пользователем"
    )
    private String hello () {
        return "Привет) Как дела?";
    }

    @Command(
            name = "Пока",
            alias = {
                    "давай", "удачи", "до скорого"
            },
            description = "Попрошаться с пользователем"
    )
    private String bye () {
        return "Пока) Увидимся позже";
    }

    @Command(
            name = "help",
            alias = {
                    "помоги", "че делать", "что можешь"
            },
            description = "Показать все доступные команды"
    )
    private String help () {
        Class botClass = this.getClass();
        StringBuilder builder = new StringBuilder();
        Method[] methods = botClass.getDeclaredMethods();
        for (Method method: methods) {
            Command command = method.getAnnotation(Command.class);
            if (command != null) {
                String cmdName = command.name();
                String cmdDesc = command.description();
                builder.append(cmdName).append("\n").append(cmdDesc).append("\n").append("--------").append("\n\n");
            }
        }
        return builder.toString();
    }

    public String communicate (String message) {
        Class botClass = this.getClass();
        Method[] methods = botClass.getDeclaredMethods();
        String result = "";
        for (Method method: methods) {
            Command command = method.getAnnotation(Command.class);
            if (command != null) {
                String cmdName = command.name().toLowerCase();
                String[] alias = command.alias();
                String formattedMessage = message.toLowerCase();
                boolean isAliasMentioned = Stream.of(alias).anyMatch(formattedMessage::contains);
                if (formattedMessage.contains(cmdName) || isAliasMentioned) {
                    try {
                        result = (String) method.invoke(this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
