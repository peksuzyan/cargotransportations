package cargotransportations.dao.manager;

import java.util.List;

public interface Manager<T> {
    T create();
    T read(int id);
    void delete(int id);
    List<T> readAll();
    void close();
}
