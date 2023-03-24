package sg.edu.nus.iss.day35server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day35server.models.Order;
import sg.edu.nus.iss.day35server.repository.OrderRepo;

@Service
public class OrderService {
    
    @Autowired
    OrderRepo oRepo;

    // method to include generate and include OrderId BEFORE saving order
    public String createOrder(Order order) {
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        order.setOrderId(orderId);
        oRepo.createOrder(order);
        return orderId;
    }
}
