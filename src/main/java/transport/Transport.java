package transport;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Transport {

    public Response doPost(String path, JSONObject obj, RequestSpecification spec) {
        return given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(obj.toString())
                .when()
                .log().all()
                .post(path)
                .then()
                .extract().response();
    }

    public Response doPut(String path, JSONObject obj, RequestSpecification spec) {
        return given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(obj.toString())
                .when()
                .log().all()
                .put(path)
                .then()
                .extract().response();
    }

    public Response doDelete(String path, RequestSpecification spec) {
        return given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .delete(path)
                .then()
                .extract().response();
    }

    public Response doGet(String path, RequestSpecification spec) {
        return given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .get(path)
                .then()
                .extract().response();
    }
}