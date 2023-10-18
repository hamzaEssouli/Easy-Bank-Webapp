package ma.youcode.easybank.dao.interfaces;

import java.util.List;

import ma.youcode.easybank.dto.Client;
import ma.youcode.easybank.dto.Employee;

public interface ClientDAO extends Dao<Client, Integer> {
    
    List<Client> getByEmployee(Employee employee);
}
