package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restservice.helpers.AssertionsHelper;
import restservice.RequestService;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;

import java.util.Map;

public class CreateAdminTest {

    private RequestService requestService = RequestService.getInstance();
    private CreateRequest createReq;

    @BeforeClass
    public void setup() {
        createReq = new CreateRequest.Builder()
                .buildAge("17")
                .buildGender("male")
                .buildLogin("user5")
                .buildPassword("1234567")
                .buildScreenName("Use3")
                .build();
    }

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
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @DataProvider(name = "validRoles")
    public Object[][] validRoles() {
        return new Object[][] {
                { "admin" },
                { "user" }
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
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);
        CreateResponse actualResp = respCP.as(CreateResponse.class);
        CreateResponse expectedResp = rCP.toCreateResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        Assert.assertEquals(actualResp, expectedResp, "Fields aren't equal");
    }
}