import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getUserReturns200() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue());
    }

    @Test
    public void missingUserReturns404() {
        given()
                .when()
                .get("/users/9999")
                .then()
                .statusCode(404);
    }

    @Test
    public void createPostReturns201() {
        String requestBody = """
                {
                  "title": "Wallet transfer test",
                  "body": "Sending 500 rupees",
                  "userId": 1
                }
                """;

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("Wallet transfer test"))
                .body("id", notNullValue());
    }
    @Test(enabled = false)  // JSONPlaceholder does not validate input: returns 201, not 400

    public void postWithoutTitleShouldBeRejected() {
        given()
                .header("Content-Type", "application/json")
                .body("{\"userId\": 1}")
                .when()
                .post("/posts")
                .then()
                .statusCode(400);
    }
}