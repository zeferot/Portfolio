package restassured;

import entities.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasKey;

public class CreateUserTest {

    @Before
    public void beforeMethod() {
        RestAssured.baseURI = "http://151.80.70.42";
        RestAssured.basePath = "/api/users";
        RestAssured.port = 3000;
    }

    @Test
    public void createStudent() {
        String name = "Hello-1234";
        String phone = "123452574567";
        String role = "Student";
        int id;
        Response response = given()
                .body("{\"name\":\"" + name + "\",\"phone\":\"" + phone + "\",\"role\":\"" + role + "\"}")
                .contentType(ContentType.JSON)
                .when()
                .post();
        response.then()
                .body("$", hasKey("id"))
                .body("$", hasKey("role"))
                .body("$", hasKey("name"))
                .body("$", hasKey("phone"))
                .statusCode(200);
        id = new JsonPath(response.getBody().asString()).getInt("id");

        User[] users = when()
                .get()
                .then()
                .extract()
                .body()
                .as(User[].class);

        List<User> userList = Arrays.asList(users);

        boolean userExists = false;
        for (User user: userList) {
            if (user.getId() == id) {
                userExists = true;
            }
        }
        Assert.assertTrue(userExists);
    }

    @Test
    public void readStudents() {
        Response response = when().get("");
//        response.body().print();
    }

    @Test
    public void updateStudent() {
        String name = "Hello-1234";
        String phone = "123452574567";
        String role = "Student";
        int id;
        Response response = given()
                .body("{\"name\":\"" + name + "\",\"phone\":\"" + phone + "\",\"role\":\"" + role + "\"}")
                .contentType(ContentType.JSON)
                .when()
                .post();
        String updatedName = "Hello-4321";
        String updatedPhone = "987654321";
        String updatedRole = "Student";
        id = new JsonPath(response.getBody().asString()).getInt("id");
        Response updatedResponse = given()
                .body("{\"name\":\"" + updatedName + "\",\"phone\":\"" + updatedPhone + "\",\"role\":\"" + updatedRole + "\",\"id\":" + id + "}")
                .contentType(ContentType.JSON)
                .when()
                .put("/"+ id +"");
        updatedResponse.then()
                .statusCode(204);
    }

    @Test
    public void deleteStudent() {
        String name = "Hello-1234";
        String phone = "123452574567";
        String role = "Student";
        int id;
        Response response = given()
                .body("{\"name\":\"" + name + "\",\"phone\":\"" + phone + "\",\"role\":\"" + role + "\"}")
                .contentType(ContentType.JSON)
                .when()
                .post();
        String deleteName = "Hello-1234";
        String deletePhone = "123452574567";
        String deleteRole = "Student";
        id = new JsonPath(response.getBody().asString()).getInt("id");
        Response deleteResponse = given()
                .body("{\"name\":\"" + deleteName + "\",\"phone\":\"" + deletePhone + "\",\"role\":\"" + deleteRole + "\",\"id\":" + id + "}")
                .contentType(ContentType.JSON)
                .when()
                .delete("/"+ id +"");
        deleteResponse.then()
                .statusCode(204);
    }

}
