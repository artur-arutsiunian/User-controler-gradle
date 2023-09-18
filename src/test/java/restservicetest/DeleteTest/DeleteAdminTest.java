package restservicetest.DeleteTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userDelete.DeleteRequest;

import java.util.Map;

import static org.testng.Assert.assertTrue;

public class DeleteAdminTest {

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
                {"admin"},
                {"user"}
        };
    }

    @Test(dataProvider = "validRoles", description = "Send role of user who can be deleted by 'admin'")
    @Description("Remove user by role 'admin'")
    public void deleteUserByAdminPositiveTest(String role) {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole(role)
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        DeleteRequest pCP = new DeleteRequest.Builder()
                .buildPlayerId(id)
                .build();
        Response respPS = requestService.send(pCP, "admin");
        AssertionsHelper.assertStatusCodeNoContent(respPS);

        String responseBody = respPS.asString();
        assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }
}
