package Control;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface RepositoryFileHelper {
    Map<String, Object> loadBeans();
    void saveEntities(Map<String, Object> entries);
}
