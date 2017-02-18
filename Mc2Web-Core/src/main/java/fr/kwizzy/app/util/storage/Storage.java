package fr.kwizzy.app.util.storage;

/**
 * Par Alexis le 12/11/2016.
 */
public interface Storage
{
    static Storage getStorage(String pathFile)
    {
        return new JSONStorage(pathFile);
    }

    Object get(String path);

    String getString(String path);

    <T> T getObject(Class<? extends T> clazz, String path);

    void put(String path, Object o);

    void putEntireObject(String path, Object o);

    boolean remove(String path);

    boolean contain(String path);

    void save();

    void disable();

}
