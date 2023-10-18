package ma.youcode.easybank.controllers;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.easybank.dto.Client;
import ma.youcode.easybank.dto.Employee;
import ma.youcode.easybank.services.ClientService;

@WebServlet(name = "client", value = "/client/*")
public class ClientController extends HttpServlet {

    @Inject
    private ClientService clientService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        String idParam = request.getParameter("id");
        String addParam = request.getParameter("add");
        String editParam = request.getParameter("edit");
        String deleteParam = request.getParameter("delete");
    
        if (idParam != null) {
            Client foundedClient = clientService.find(Integer.parseInt(idParam));
            request.setAttribute("client", foundedClient);
            request.getRequestDispatcher("/pages/client.jsp").forward(request, response);
        } else if (addParam != null) {
            request.getRequestDispatcher("/pages/addClient.jsp").forward(request, response);
        } else if (editParam != null) {
            Client foundedClient = clientService.find(Integer.parseInt(editParam));
            request.setAttribute("client", foundedClient);
            request.getRequestDispatcher("/pages/editClient.jsp").forward(request, response);
        } else if (deleteParam != null)
            doDelete(request, response);
        
        else {
            request.setAttribute("clients", clientService.read());
            request.getRequestDispatcher("/pages/clientManagement.jsp").forward(request, response);
        }
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateParam = request.getParameter("update");
        if( updateParam != null )
            doPut(request, response);
        else {
            String firstName = (String) request.getParameter("firstName");
            String lastName = (String) request.getParameter("lastName");
            LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
            String phoneNumber = (String) request.getParameter("phoneNumber");
            String address = (String) request.getParameter("address");
            Integer employeeId = Integer.valueOf( (String) request.getParameter("employeeId") );
            Client client = new Client();
            Employee employee = new Employee();
            employee.setId(employeeId);
            client.setLastName(lastName);
            client.setFirstName(firstName);
            client.setDateOfBirth(dateOfBirth);
            client.setPhoneNumber(phoneNumber);
            client.setAddress(address);
            client.setEmployee(employee);

            request.setAttribute("client", clientService.create(client));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/clientCreated.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf( request.getParameter("id") );
        String firstName = (String) request.getParameter("firstName");
        String lastName = (String) request.getParameter("lastName");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String phoneNumber = (String) request.getParameter("phoneNumber");
        String address = (String) request.getParameter("address");
        Integer employeeId = Integer.valueOf( (String) request.getParameter("employeeId") );
        Client client = new Client();
        Employee employee = new Employee();
        employee.setId(employeeId);
        client.setId(id);
        client.setLastName(lastName);
        client.setFirstName(firstName);
        client.setDateOfBirth(dateOfBirth);
        client.setPhoneNumber(phoneNumber);
        client.setAddress(address);
        client.setEmployee(employee);

        clientService.update(client);
        response.sendRedirect("http://mizo-laptop:8080/Easybank/client");

        
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer clientId = Integer.valueOf( request.getParameter("delete") );       
        clientService.delete(clientId);
        response.sendRedirect("http://mizo-laptop:8080/Easybank/client");
    }


}
