package ma.youcode.easybank.controllers;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.easybank.services.EmployeeService;

@WebServlet(name = "employee", value = "/employee")
public class EmployeeController extends HttpServlet {
    
    @Inject
    private EmployeeService employeeService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setAttribute("employees", employeeService.read());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/employeeManagement.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        
    }
}
