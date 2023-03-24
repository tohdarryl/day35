package sg.edu.nus.iss.day35server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {
    private String item;
    private int quantity;


// Build JsonObject using set attributes
public JsonObject toJson(){
    return Json.createObjectBuilder()
    .add("item", item)
    .add("quantity", quantity)
    .build();
}

// Use this if building JsonObject in 'Order' instead
public JsonObjectBuilder toJson2(){
    return Json.createObjectBuilder()
    .add("item", item)
    .add("quantity", quantity);
}

// Convert from JsonObject to set attributes of LineItem
public static LineItem toLineItem(JsonObject j){
    LineItem lineItem = new LineItem();
    lineItem.setItem(j.getString("item"));
    lineItem.setQuantity(j.getInt("quantity"));

    return lineItem;
}

}