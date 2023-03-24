package sg.edu.nus.iss.day35server.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.day35server.models.Order;

@Repository
public class OrderRepo {
    
    @Autowired
    MongoTemplate template;

    // Method to save/insert Order into collection/table
    public void createOrder(Order order){
        JsonObject j =  order.toJSON();
        // Document.parse(); converts JsonObject (String) into Document format
        Document doc = Document.parse(j.toString());

        template.insert(doc, "orders");


    }
}
