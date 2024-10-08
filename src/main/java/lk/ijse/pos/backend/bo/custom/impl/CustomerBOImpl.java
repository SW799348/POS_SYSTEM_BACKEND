package lk.ijse.burgershop.backend.bo.custom.impl;

import lk.ijse.burgershop.backend.bo.custom.CustomerBO;
import lk.ijse.burgershop.backend.dao.DAOFactory;
import lk.ijse.burgershop.backend.dao.custom.CustomerDAO;
import lk.ijse.burgershop.backend.dto.CustomerDTO;
import lk.ijse.burgershop.backend.entity.Customer;
import lk.ijse.burgershop.backend.util.SQLUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        try(Connection connection = SQLUtil.getConnection()) {
            customerDAO.setConnection(connection);
            return customerDAO.save(new Customer(
                    customerDTO.getCustomerId(),
                    customerDTO.getFirstName(),
                    customerDTO.getLastName(),
                    customerDTO.getDob(),
                    customerDTO.getAddress(),
                    customerDTO.getMobile()
            ));
        }
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException {
        try(Connection connection = SQLUtil.getConnection()) {
            customerDAO.setConnection(connection);
            return customerDAO.delete(customerId);
        }
    }

    @Override
    public boolean updateCustomer(String customerId, CustomerDTO customerDTO) throws SQLException {
        try (Connection connection = SQLUtil.getConnection()) {
            customerDAO.setConnection(connection);
            return customerDAO.update(customerId,
                    new Customer(customerDTO.getCustomerId(),
                            customerDTO.getFirstName(),
                            customerDTO.getLastName(),
                            customerDTO.getDob(),
                            customerDTO.getAddress(),
                            customerDTO.getMobile()
                    ));
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws SQLException {
        try (Connection connection = SQLUtil.getConnection()) {
            customerDAO.setConnection(connection);
            List<Customer> allCustomers = customerDAO.getAll();
            List<CustomerDTO> allCustomersDTO = new ArrayList<>();
            for (Customer customer : allCustomers) {
                allCustomersDTO.add(new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getDob(),
                        customer.getAddress(),
                        customer.getMobile()
                ));
            }
            return allCustomersDTO;
        }
    }
}

