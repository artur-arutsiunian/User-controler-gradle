package restservicetest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userGet.request.GetRequest;
import restservice.pojo.userGet.response.GetResponse;

import java.util.Map;

public class GetUserTest {

    private RequestService requestService = RequestService.getInstance();

    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("User5")
            .buildPassword("1234567")
            .buildScreenName("Use3")
            .build();

    @Test(description = "Receive user with correct id")
    @Description("Get a specific user by id")
    public void getUserPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        GetRequest gCP = new GetRequest.Builder()
                .buildPlayerId(id)
                .build();
        Response respPS = requestService.send(gCP);
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respPS);
        GetResponse actualResp = respPS.as(GetResponse.class);
        GetResponse expectedResp = rCP.toGetResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        Assert.assertEquals(actualResp, expectedResp, "Fields aren't equal");
    }

    @Test(description = "Receive user with wrong id")
    @Description("Get of user with wrong id")
    public void getUserNegativeTest() {
        GetRequest gCP = new GetRequest.Builder()
                .buildPlayerId(123456)
                .build();
        Response respPS = requestService.send(gCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);

        String responseBody = respPS.asString();
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }
}