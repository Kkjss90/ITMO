package org.example.utility;

import com.google.gson.*;
import org.example.data.Coordinates;
import org.example.data.Location;
import org.example.data.Position;
import org.example.data.Route;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Класс, предназначенный для парсинга
 */
public class CustomConverter implements JsonSerializer<Route>, JsonDeserializer<Route>{
    Gson gson= new Gson();
    @Override
    public Route deserialize(
            JsonElement json,
            Type type,
            JsonDeserializationContext context
    ) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        int id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        JsonObject coordinates = jsonObject.get("coordinates").getAsJsonObject();
        int x = coordinates.get("x").getAsInt();
        Float y = coordinates.get("y").getAsFloat();
        Coordinates coords = new Coordinates(x, y);
        Date creationDate = new Date();
        JsonObject from = jsonObject.get("from").getAsJsonObject();
        Double x_l = from.get("x").getAsDouble();
        Float y_l = from.get("y").getAsFloat();
        int z_l = from.get("z").getAsInt();
        String name_l = from.get("name").getAsString();
        Location location = new Location(x_l ,y_l ,z_l ,name_l);
        JsonObject to = jsonObject.get("to").getAsJsonObject();
        int x_p = to.get("x").getAsInt();
        int y_p = to.get("y").getAsInt();
        double z_p = to.get("z").getAsDouble();
        Position position = new Position(x_p, y_p, z_p);
        long distance = jsonObject.get("distance").getAsLong();

        return new Route(
                id,
                name,
                coords,
                creationDate,
                location,
                position,
                distance
        );
    }

    @Override
    public JsonElement serialize(
            Route route,
            Type type,
            JsonSerializationContext jsonSerializationContext
    ) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", route.getId());
        jsonObject.addProperty("name", route.getName());
        jsonObject.add("coordinates", gson.toJsonTree(route.getCoordinates()));
        jsonObject.add("creationDate", gson.toJsonTree(route.getCreationDate()));
        jsonObject.add("from", gson.toJsonTree(route.getFrom()));
        jsonObject.add("to", gson.toJsonTree(route.getTo()));
        jsonObject.addProperty("distance", route.getDistance());

        return jsonObject;
    }
}
