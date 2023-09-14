package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;

import java.util.Map;

public class CreateUserTest {

    private RequestService requestService = RequestService.getInstance();

    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("user5")
            .buildPassword("1234567")
            .buildScreenName("Use3")
            .build();

    @DataProvider(name = "validRoles")
    public Object[][] validRoles() {
        return new Object[][]{
                {"supervisor"},
                {"admin"},
                {"user"}
        };
    }

    @Test(dataProvider = "validRoles", description = "Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    public void verifyingRoleNegativeTest(String role) {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole(role)
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "user");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);

        String responseBody = respCP.asString();
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }
}