package Control;

public interface Repository {
    void save();
    Object get(String id);
    void set(String id, Object obj);
    void delete(String id);
}
