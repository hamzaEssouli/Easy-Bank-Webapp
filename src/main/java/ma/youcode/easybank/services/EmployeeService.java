package ma.youcode.easybank.services;

import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.youcode.easybank.dao.EmployeeDAOImp;
import ma.youcode.easybank.dto.Client;
import ma.youcode.easybank.dto.Employee;

@ApplicationScoped
public class EmployeeService {
    
    @Inject
    private EmployeeDAOImp employeeDAOImp;

    @Inject
    private ClientService clientService;
    

    public Employee create(Employee employee) {
        Optional<Employee> optionalEmployee = employeeDAOImp.create(employee);
        return optionalEmployee.isPresent() ? 
            optionalEmployee.get()
        :
            null;
    }
    
    public boolean delete(Integer clientId) {
        return employeeDAOImp.delete(clientId);
    }

    public Employee find(Integer clientId) {
        Optional<Employee> optionalEmployee = employeeDAOImp.find(clientId);
        return optionalEmployee.isPresent() ?
            optionalEmployee.get()
        :
            null;
    }

    public List<Employee> read() {
        return employeeDAOImp.read();
    }

    public Employee update(Employee employee) {
        Optional<Employee> optionalEmployee = employeeDAOImp.update(employee);
        return optionalEmployee.isPresent() ?
            optionalEmployee.get()
        :
            null;
    } 

    public <Attribute> List<Employee> search(String attributeName, Attribute attributeValue) {
        return employeeDAOImp.search(attributeName, attributeValue);
    }

    public List<Client> getEmployeeClients(Employee employee ) {
        List<Client> clients = clientService.getByEmployee(employee);
        return clients;
    }


}
