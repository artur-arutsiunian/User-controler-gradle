package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restservice.helpers.AssertionsHelper;
import restservice.RequestService;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userGet.response.PlayerResponse;
import restservice.pojo.userGet.response.PlayersResponse;

import java.util.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateAdminTest {

    private RequestService requestService = RequestService.getInstance();

       CreateRequest createReq = new CreateRequest.Builder()
                .buildAge("17")
                .buildGender("male")
                .buildLogin("user5")
                .buildPassword("1234567")
                .buildScreenName("Use3")
                .build();

    @Test(description = "Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    public void verifyingRoleSupervisorNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("supervisor")
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "admin");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);

        String responseBody = respCP.asString();
        assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @DataProvider(name = "validRoles")
    public Object[][] validRoles() {
        return new Object[][]{
                {"admin"},
                {"user"}
        };
    }

    @Test(dataProvider = "validRoles", description = "Create user with different roles")
    @Description("Create a user with different roles and validate the response")
    public void createUserWithValidRolePositiveTest(String role) {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole(role)
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "admin");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);
        CreateResponse actualResp = respCP.as(CreateResponse.class);
        CreateResponse expectedResp = rCP.toCreateResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        assertEquals(actualResp, expectedResp, "Fields aren't equal");

        Response listRQ = requestService.send();
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(listRQ);
        PlayersResponse actualListResp = listRQ.as(PlayersResponse.class);
        List<PlayerResponse> actualPlayers = actualListResp.getPlayers();

        PlayerResponse expectedPlayer = new PlayerResponse(
                expectedResp.getId(),
                expectedResp.getAge(),
                expectedResp.getGender(),
                expectedResp.getRole(),
                expectedResp.getScreenName());

        assertTrue(actualPlayers.contains(expectedPlayer));
    }
}