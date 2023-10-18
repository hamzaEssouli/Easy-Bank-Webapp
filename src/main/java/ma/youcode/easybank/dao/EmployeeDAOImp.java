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
import ma.youcode.easybank.dao.interfaces.EmployeeDAO;
import ma.youcode.easybank.dto.Employee;

@ApplicationScoped
public class EmployeeDAOImp implements EmployeeDAO {

    @Inject
    private Connection connection;

    @Override
    public Optional<Employee> create(Employee employee) {
        String insertQuery = "INSERT INTO Employees( firstName, lastName, dateOfBirth, phoneNumber, recruitmentDate, email )" +
                             "VALUES( ? , ? , ? , ? , ?, ? )";
        try( PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS) ) {
            preparedStatement.setString(1, employee.getLastName());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setObject(3, employee.getDateOfBirth());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setObject(5, employee.getRecruitmentDate());
            preparedStatement.setString(6, employee.getEmail());
            
            if( preparedStatement.executeUpdate() > 0 ) {
                ResultSet generatedKey = preparedStatement.getGeneratedKeys();
                if( generatedKey.next() ) {
                    int generatedId = generatedKey.getInt(1);
                    employee.setId(generatedId);
                    return Optional.of(employee);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }

        return Optional.empty();
    }

    @Override
    public Optional<Employee> update(Employee employee) {
        String updateQuery = "UPDATE employees SET\n" + //
                "firstName = ?,\n" + //
                "lastName = ?,\n" + //
                "dateOfBirth = ?,\n" + //
                "phoneNumber = ?,\n" + //
                "recruitmentDate = ?,\n" + //
                "email = ?\n" + //
                "WHERE id = ?;";
        
        try( PreparedStatement preparedStatement = connection.prepareStatement(updateQuery) ) {
            preparedStatement.setString(1, employee.getLastName());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setObject(3, employee.getDateOfBirth());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setObject(5, employee.getRecruitmentDate());
            preparedStatement.setString(6, employee.getEmail());
            preparedStatement.setInt(7, employee.getId());   

                
                if( preparedStatement.executeUpdate() > 0 ) 
                    return Optional.of(employee);
        } catch( Exception e ) { e.printStackTrace(); }

        return Optional.empty();
    }

    @Override
    public List<Employee> read() {
        String readQuery = "SELECT * FROM Employees";
        List<Employee> employees = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(readQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                Employee employee = new Employee();
                employee.setId( resultSet.getInt("id") );
                employee.setLastName(resultSet.getString("lastName"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setDateOfBirth(LocalDate.parse(resultSet.getDate("dateOfBirth").toString()));
                employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                employee.setRecruitmentDate(LocalDate.parse(resultSet.getDate("recruitmentDate").toString()));
                employee.setEmail(resultSet.getString("email"));

                employees.add(employee);
            }
        } catch( Exception e ) { e.printStackTrace(); }

        return employees;
    }

    @Override
    public Boolean delete(Integer employeeId) {
        String deleteQuery = "DELETE FROM employees WHERE id = ?";
        int deletedCount = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, employeeId);

            deletedCount = preparedStatement.executeUpdate();
        } catch( SQLException e ) { e.printStackTrace(); }
        return (deletedCount > 0) ? true : false;
    }

    @Override
    public Optional<Employee> find(Integer employeeId) {
        String searchByRegistrationCodeQuery = "SELECT * FROM Employees WHERE id = ?"; 
        try(PreparedStatement preparedStatement = connection.prepareStatement(searchByRegistrationCodeQuery)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet result = preparedStatement.executeQuery();
            if( result.next() ) {
                Employee employee = new Employee();
                employee.setId(result.getInt("id"));
                employee.setLastName(result.getString("lastName"));
                employee.setFirstName(result.getString("firstName"));
                employee.setDateOfBirth(LocalDate.parse(result.getDate("dateOfBirth").toString()));
                employee.setPhoneNumber(result.getString("phoneNumber"));
                employee.setRecruitmentDate(LocalDate.parse(result.getDate("recruitmentDate").toString()));
                employee.setEmail(result.getString("email"));

                return Optional.of(employee);
            }
        } catch( SQLException e ) { e.printStackTrace(); }
        
        return Optional.empty();
    }



    @Override
    public <Attribute> List<Employee> search(String attributeName, Attribute attributeValue) {
        String searchQuery = "SELECT * FROM Employees WHERE ? = ?";
        List<Employee> employees = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {
            preparedStatement.setString(1, attributeName);
            preparedStatement.setObject(2, attributeValue, Types.JAVA_OBJECT);
           
            ResultSet resultSet = preparedStatement.executeQuery();
            while( resultSet.next() ) {
                Employee employee = new Employee();
                employee.setId( resultSet.getInt("id") );
                employee.setLastName(resultSet.getString("lastName"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setDateOfBirth(LocalDate.parse(resultSet.getDate("dateOfBirth").toString()));
                employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                employee.setRecruitmentDate(LocalDate.parse(resultSet.getDate("recruitmentDate").toString()));
                employee.setEmail(resultSet.getString("email"));

                employees.add(employee);
            }
        } catch(SQLException e) { e.printStackTrace(); }

        return employees;
    }
    
    
}
