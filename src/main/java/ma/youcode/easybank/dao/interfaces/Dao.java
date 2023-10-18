package ma.youcode.easybank.dao.interfaces;

import java.util.List;
import java.util.Optional;


public interface Dao<T, P> {
    
    Optional<T> create(T object);
    List<T> read();
    Optional<T> update(T updatedObject);
    Boolean delete(P id);

    Optional<T> find(P id);
    <Attribute> List<T> search(String attributeName, Attribute attributeValue);
}
