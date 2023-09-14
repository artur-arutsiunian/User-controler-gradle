package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;

import java.util.Map;

public class CreateSupervisorTest {

    private RequestService requestService = RequestService.getInstance();
    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("user5")
            .buildPassword("1234567")
            .buildRole("user")
            .buildScreenName("Use3")
            .build();

    @DataProvider(name = "validRoles")
    public Object[][] validRoles() {
        return new Object[][]{
                {"admin"},
                {"user"}
        };
    }

    @Test(dataProvider = "validRoles", description = "Check user creation")
    @Description("Check that user creates with all necessary fields and values")
    @Severity(value = SeverityLevel.BLOCKER)
    public void createUserPositiveTest(String role) {
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

    @DataProvider(name = "invalidAges")
    public Object[][] invalidAges() {
        return new Object[][]{
                {"60"},
                {"16"},
                {"0"},
                {"1"},
                {"61"}
        };
    }

    @Test(dataProvider = "invalidAges", description = "Send wrong 'age' field")
    @Description("Send wrong user age")
    public void verifyingAgeNegativeTest(String age) {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildAge(age)
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test(description = "Send wrong 'password' field")
    @Description("Send wrong user password")
    public void verifyingPasswordNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildPassword("123456")
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test(description = "Send 'supervisor' role who can't be created")
    @Description("Send 'supervisor' role who can't be created")
    public void verifyingRoleSupervisorNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("supervisor")
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test(description = "Send 'login' which was already used")
    @Description("Send 'login' field which was used before")
    public void verifyingUniqueLoginFieldNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildLogin("user5")
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test(description = "Send 'screenName' which was already used")
    @Description("Send 'screenName' field which was used before")
    public void verifyingUniqueScreenNameFieldNegative() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildScreenName("Use3")
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test(description = "Send another correct user gender")
    @Description("Send another correct option gender of user")
    public void verifyingGenderFieldPositive() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildGender("female")
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