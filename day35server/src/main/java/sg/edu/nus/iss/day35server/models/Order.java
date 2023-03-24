package sg.edu.nus.iss.day35server.models;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String name;
    private String email;
    private List<LineItem> lineItems = new LinkedList<>();


    public JsonObject toJSON(){
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        // converting List object 'lineItems' into JsonArray
        lineItems.stream()
                    .forEach(v -> arrBuilder.add(v.toJson2()));

                    return Json.createObjectBuilder()
                            .add("orderId", orderId)
                            .add("name", name)
                            .add("email", email)
                            .add("lineItems", arrBuilder)
                            .build();
    }

    public static Order toOrder(String j){
        // Create JsonReader for String payload
        JsonReader reader = Json.createReader(new StringReader(j));
        return toOrder(reader.readObject());
    }

    // After converting JsonReader to JsonObject, use JsonObject to set values for Order
    public static Order toOrder(JsonObject j) {

		Order order = new Order();
		if (j.containsKey("orderId") && (!j.isNull("orderId")))
			order.setOrderId(j.getString("orderId"));
		order.setName(j.getString("name"));
		order.setEmail(j.getString("email"));
		List<LineItem> lineItems = j.getJsonArray("lineItems").stream()
			.map(i -> i.asJsonObject())
			.map(LineItem::toLineItem)
			.toList();
		order.setLineItems(lineItems);
		return order;
	}
}
