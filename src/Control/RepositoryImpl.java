package Control;

import java.util.Map;

public class RepositoryImpl implements Repository {
    private final RepositoryFileHelper fileHelper;
    private final Map<String, Object> entities;

    public RepositoryImpl(RepositoryFileHelper fileHelper) {
        this.fileHelper = fileHelper;
        this.entities = fileHelper.loadBeans();
    }

    @Override
    public void save() {
        fileHelper.saveEntities(entities);
    }

    @Override
    public Object get(String id) {
        return entities.get(id);
    }

    @Override
    public void set(String id, Object obj) {
        entities.put(id, obj);
    }

    @Override
    public void delete(String id) {
        entities.remove(id);
    }
}
