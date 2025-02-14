package services;

import domain.Employee;
import dao.EmployeeDAO;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public Employee findEmployeeByCpf(String cpf) {
        return employeeDAO.findByCpf(cpf);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }
} 