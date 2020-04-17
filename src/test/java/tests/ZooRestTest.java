package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import specification.SpecCreator;

import static assertion.AssertCode.assertStatusCode;
import static assertion.SchemaValidator.checkJsonSchema;
import static java.lang.String.format;
import static json.Animals.newAnimal;
import static org.apache.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ZooRestTest extends BaseTest {

    private int animalId = 7;

    @Test
    @Order(1)
    @DisplayName("Animal creation check")
    public void createAnimal() {
        Response response = transport.doPost(animalsPath, newAnimal(animalId, "Zebra", "Dasha", 13) ,new SpecCreator.Builder()
                .build().createSpec());
        assertStatusCode(response, "Create animal", SC_OK);
        checkJsonSchema(response,"schemas/Zoo.json");
        Response resp = transport.doGet(format("%s/%s", animalsPath, animalId), new SpecCreator.Builder().build().createSpec());
        assertStatusCode(resp, "Get animal by id", SC_OK);
        checkJsonSchema(resp,"schemas/Zoo.json");
        JsonPath jsonPath = resp.jsonPath();
        assertEquals("Dasha", jsonPath.get("name"));
        assertEquals("13", jsonPath.get("age"));
    }

    @Test
    @Order(2)
    @DisplayName("Animal update check")
    public void updateAnimal() {
        Response response = transport.doPut(format("%s/%s", animalsPath, animalId)
                , newAnimal(animalId, "Deer", "Masha", 8)
                , new SpecCreator.Builder()
                .build().createSpec());
        assertStatusCode(response, "Update animal", SC_OK);
        checkJsonSchema(response,"schemas/Zoo.json");
        JsonPath jsonPathResponse = response.jsonPath();
        assertEquals("Masha", jsonPathResponse.get("name"));
        assertEquals("Deer", jsonPathResponse.get("type"));
        Response resp = transport.doGet(format("%s/%s", animalsPath, animalId), new SpecCreator.Builder().build().createSpec());
        assertStatusCode(resp, "Get animal by id", SC_OK);
        checkJsonSchema(resp,"schemas/Zoo.json");
        JsonPath jsonPath = resp.jsonPath();
        assertEquals(Integer.valueOf(animalId), jsonPath.get("id"));
        assertEquals("8", jsonPath.get("age"));
    }

    @Test
    @Order(3)
    @DisplayName("Animal list check")
    public void getAnimalsList() {
        Response response = transport.doGet(animalsListPath, new SpecCreator.Builder()
                        .build().createSpec());
        assertStatusCode(response, "Get animal list", SC_OK);
        checkJsonSchema(response,"schemas/ZooList.json");
        JsonPath jsonPath = response.jsonPath();
        assertEquals(Integer.valueOf(1), jsonPath.get("count"));
    }

    @Test
    @Order(4)
    @DisplayName("Animal delete check")
    public void deleteAnimal() {
        Response response = transport.doDelete(format("%s/%s", animalsPath, animalId), new SpecCreator.Builder()
                .build().createSpec());
        assertStatusCode(response, "Delete animal", SC_NO_CONTENT);
        Response resp = transport.doGet(format("%s/%s", animalsPath, animalId), new SpecCreator.Builder().build().createSpec());
        assertStatusCode(resp, "Get animal by id", SC_NOT_FOUND);
    }
}
