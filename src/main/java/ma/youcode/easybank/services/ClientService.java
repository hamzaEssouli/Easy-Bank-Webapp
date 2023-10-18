package ma.youcode.easybank.services;

import java.util.List;
import java.util.Optional;



import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.youcode.easybank.dao.ClientDAOImp;
import ma.youcode.easybank.dto.Client;
import ma.youcode.easybank.dto.Employee;


@ApplicationScoped
public class ClientService {

    @Inject
    private ClientDAOImp clientDAOImp;



    public Client create(Client client) {
        Optional<Client> optionalClient = clientDAOImp.create(client);
        return optionalClient.isPresent() ? 
            optionalClient.get()
        :
            null;
    }

    public Boolean delete(Integer clientId) {
        return clientDAOImp.delete(clientId);
    }

    public Client find(Integer clientId) {
        Optional<Client> optionalClient = clientDAOImp.find(clientId);
        return optionalClient.isPresent() ?
            optionalClient.get()
        :
            null;
    }

    public List<Client> read() {
        return clientDAOImp.read();
    }

    public <S> List<Client> search(String attributName, S attributeValue) {
        return clientDAOImp.search(attributName.toLowerCase(), attributeValue);
    }

    public Client update(Client client) {
        Optional<Client> optionalClient = clientDAOImp.update(client);
        return optionalClient.isPresent() ?
            optionalClient.get()
        :
            null;
    }
    
    public List<Client> getByEmployee(Employee employee) {
        return clientDAOImp.getByEmployee(employee);
    }
}
