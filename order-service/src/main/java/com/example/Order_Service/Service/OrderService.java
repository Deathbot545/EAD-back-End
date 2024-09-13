package com.example.Order_Service.Service;

import com.example.Order_Service.Model.Order;
import com.example.Order_Service.Model.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Place a new order
    public Order placeOrder(Order order) {
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    // Get order by ID
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }

    // Get orders by buyer ID
    public List<Order> getOrdersByBuyerId(Integer buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }

    // Get orders by freelancer ID
    public List<Order> getOrdersByFreelancerId(Integer freelancerId) {
        return orderRepository.findByFreelancerId(freelancerId);
    }

    // Update the status of an order
    public Order updateOrderStatus(Integer id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        order.setStatus(status);
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return orderRepository.save(order);
    }

    // Cancel an order by setting its status to "CANCELLED"
    public void cancelOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        order.setStatus("CANCELLED");
        order.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        orderRepository.save(order);
    }
}