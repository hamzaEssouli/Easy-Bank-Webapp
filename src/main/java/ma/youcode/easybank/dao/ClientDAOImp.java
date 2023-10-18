package ma.youcode.easybank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.youcode.easybank.dao.interfaces.ClientDAO;
import ma.youcode.easybank.dto.Client;
import ma.youcode.easybank.dto.Employee;



@ApplicationScoped
public class ClientDAOImp implements ClientDAO {

    @Inject
    private EmployeeDAOImp employeeDAOImp;

    @Inject
    private Connection connection;

    @Override
    public Optional<Client> create(Client client) {
        String insertQuery = "INSERT INTO Clients( firstName, lastName, dateOfBirth, phoneNumber, address, employeeId )" +
                             "VALUES( ? , ? , ? , ? , ?, ? )";
        try( PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS) ) {
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setObject(3, client.getDateOfBirth());
            preparedStatement.setString(4, client.getPhoneNumber());
            preparedStatement.setObject(5, client.getAddress());
            preparedStatement.setInt(6, client.getEmployee().getId());
            
            if( preparedStatement.executeUpdate() > 0 ) {
                ResultSet generatedKey = preparedStatement.getGeneratedKeys();
                if( generatedKey.next() ) {
                    int generatedId = generatedKey.getInt(1);
                    client.setId(generatedId);
                    client.setEmployee( employeeDAOImp.find( client.getEmployee().getId() ).get() );
                    return Optional.of(client);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client client) {
        String updateQuery = "UPDATE Clients SET\n" + //
                "firstName = ?,\n" + //
                "lastName = ?,\n" + //
                "dateOfBirth = ?,\n" + //
                "phoneNumber = ?,\n" + //
                "address = ?\n" + //
                "WHERE id = ?;";
        
        try( PreparedStatement preparedStatement = connection.prepareStatement(updateQuery) ) {
            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setObject(3, client.getDateOfBirth());
            preparedStatement.setString(4, client.getPhoneNumber());
            preparedStatement.setObject(5, client.getAddress());
            preparedStatement.setInt(6, client.getId());   

                
            if( preparedStatement.executeUpdate() > 0 ) 
            return Optional.of(client);
        } catch( Exception e ) { e.printStackTrace(); }

        return Optional.empty();
    }

    @Override
    public List<Client> read() {
        String readQuery = "SELECT * FROM Clients";
        List<Client> clients = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(readQuery)) {
            ResultSet result = preparedStatement.executeQuery();
            while( result.next() ) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setLastName(result.getString("lastName"));
                client.setFirstName(result.getString("firstName"));
                client.setDateOfBirth(LocalDate.parse(result.getDate("dateOfBirth").toString()));
                client.setPhoneNumber(result.getString("phoneNumber"));
                client.setAddress(result.getString("address"));
                client.setEmployee( employeeDAOImp.find( result.getInt("employeeId") ).get() );

                clients.add(client);
            }
        } catch( Exception e ) { e.printStackTrace(); }

        return clients;
    }

    @Override
    public Boolean delete(Integer id) {
        String deleteQuery = "DELETE FROM clients WHERE id = ?";
        int deletedCount = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);

            deletedCount = preparedStatement.executeUpdate();
        } catch( SQLException e ) { e.printStackTrace(); }
        return (deletedCount > 0) ? true : false;
    }


    @Override
    public Optional<Client> find(Integer id) {
        String searchByRegistrationCodeQuery = "SELECT * FROM Clients WHERE id = ?"; 
        try(PreparedStatement preparedStatement = connection.prepareStatement(searchByRegistrationCodeQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if( result.next() ) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setLastName(result.getString("lastName"));
                client.setFirstName(result.getString("firstName"));
                client.setDateOfBirth(LocalDate.parse(result.getDate("dateOfBirth").toString()));
                client.setPhoneNumber(result.getString("phoneNumber"));
                client.setAddress(result.getString("address"));
                client.setEmployee( employeeDAOImp.find( result.getInt("employeeId") ).get() );

                return Optional.of(client);
            }
        } catch( SQLException e ) { e.printStackTrace(); }
        
        return Optional.empty();
    }

    @Override
    public List<Client> getByEmployee(Employee employee) {
        String getByEmployeeQuery = "SELECT * FROM Clients WEHER employeeId = ?";
        List<Client> clients = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(getByEmployeeQuery)) {
            preparedStatement.setInt(1, employee.getId());

            ResultSet result = preparedStatement.executeQuery();
            while( result.next() ) {
                Client client = new Client();
                client.setId(result.getInt("id"));
                client.setLastName(result.getString("lastName"));
                client.setFirstName(result.getString("firstName"));
                client.setDateOfBirth(LocalDate.parse(result.getDate("dateOfBirth").toString()));
                client.setPhoneNumber(result.getString("phoneNumber"));
                client.setAddress(result.getString("address"));

                clients.add(client);
            }
        } catch( Exception e ) { e.printStackTrace(); }

        return clients;
    }

    @Override
    public <S> List<Client> search(String attributeName, S attributeValue) {
        String searchQuery = "SELECT * FROM Clients WHERE ? = ?";
        List<Client> clients = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, attributeName);
            preparedStatement.setObject(2, preparedStatement, Types.JAVA_OBJECT);

            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                Client client = new Client();
                client.setId( resultSet.getInt("id") );
                client.setLastName(resultSet.getString("lastName"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setDateOfBirth(LocalDate.parse(resultSet.getDate("dateOfBirth").toString()));
                client.setPhoneNumber(resultSet.getString("phoneNumber"));
                client.setAddress(resultSet.getString("address"));
                client.setEmployee( employeeDAOImp.find( resultSet.getInt("employeeId") ).get() );

                clients.add(client);
            }
        } catch(SQLException e) { e.printStackTrace(); System.exit(0); }

        return clients;
    }
  
    
    
}
