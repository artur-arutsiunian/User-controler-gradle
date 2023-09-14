package restservice;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restservice.pojo.userDelete.DeleteRequest;
import restservice.pojo.userGet.request.GetRequest;
import restservice.pojo.userPatch.request.PatchRequest;

import java.util.Map;

public class RequestService {

    private static RequestService instance = new RequestService();

    public static RequestService getInstance() {
        return instance;
    }

    public RequestSpecification given() {
        return RestAssured.given()
                .baseUri("http://3.68.165.45")
                .basePath("/player")
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured())
                .relaxedHTTPSValidation();
    }

    public Response send(Map<String, Object> requestMap, String role) {
        return given().queryParams(requestMap)
                .get("/create/" + role);
    }

    public Response send(PatchRequest rq, String role, int userId) {
        return given().body(rq)
                .patch("/update/" + role + userId);
    }

    public Response send(DeleteRequest dl, String role) {
        return given().body(dl)
                .delete("/delete/" + role);
    }

    public Response send(GetRequest gr) {
        return given().body(gr)
                .post("/get");
    }
}
