package services;

import domain.Order;
import dao.OrderDAO;
import java.util.List;

public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }
} 