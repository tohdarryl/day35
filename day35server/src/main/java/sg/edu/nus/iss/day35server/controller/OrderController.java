package sg.edu.nus.iss.day35server.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.day35server.models.Order;
import sg.edu.nus.iss.day35server.service.OrderService;

@Controller
@RequestMapping(path="/order", produces=MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins="*")
public class OrderController {
    
    private Logger logger = Logger.getLogger(OrderController.class.getName());

    @Autowired
    OrderService orderSvc;

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postOrder(@RequestBody String payload){

        logger.info("New order: %s".formatted(payload));

        Order order = Order.toOrder(payload);

        String orderId = orderSvc.createOrder(order);

        JsonObject resp = Json.createObjectBuilder()
                        .add("orderId", orderId)
                        .build();
        
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
    }


}
