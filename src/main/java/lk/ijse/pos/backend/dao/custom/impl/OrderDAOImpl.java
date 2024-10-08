package lk.ijse.burgershop.backend.dao.custom.impl;

import lk.ijse.burgershop.backend.dao.custom.OrderDAO;
import lk.ijse.burgershop.backend.entity.Order;
import lk.ijse.burgershop.backend.util.SQLUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;


    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public boolean save(Order order) throws SQLException {
        return SQLUtil.execute(connection, "INSERT INTO orders VALUES(?,?,?,?,?,?)",
                order.getOrderId(),
                order.getDateAndTime(),
                order.getCustomerId(),
                order.getSubtotal(),
                order.getDiscount(),
                order.getAmount_payed()
        );
    }

    @Override
    public boolean update(String id, Order order) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Order search(String id) {
        return null;
    }

    @Override
    public String getId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute(connection,"SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if(resultSet.next()){
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }


}
