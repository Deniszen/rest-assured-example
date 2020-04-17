package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import models.animal.Animal;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class Animals {

    private Animals() {}

    private static final Logger LOGGER = Logger.getLogger(Animals.class.getName());

    public static JSONObject newAnimal(int id, String type, String name, int age) {
        Animal animal = new Animal();
        animal.setId(id);
        animal.setType(type);
        animal.setName(name);
        animal.setAge(age);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return new JSONObject(ow.writeValueAsString(animal));
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return new JSONObject();
    }
}
