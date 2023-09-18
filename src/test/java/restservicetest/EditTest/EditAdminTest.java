package restservicetest.EditTest;

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
import restservice.pojo.userPatch.request.PatchRequest;
import restservice.pojo.userPatch.response.PatchResponse;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class EditAdminTest {

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

    @Test(dataProvider = "validRoles", description = "Change user age by admin")
    @Description("Change user age by admin")
    public void editUserAgeByAdminPositiveTest(String role) {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole(role)
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .buildPlayerAge(25)
                .build();
        Response respPS = requestService.send(pCP, "admin/", id);
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respPS);
        PatchResponse actualResp = respPS.as(PatchResponse.class);
        assertEquals(actualResp.getAge(), pCP.getAge(), "'Age' fields aren't equal");
        assertEquals(actualResp.getGender(), rCP.getGender(), "'Gender' fields aren't equal");
        assertEquals(actualResp.getLogin(), rCP.getLogin(), "'Login' fields aren't equal");
        assertEquals(actualResp.getRole(), rCP.getRole(), "'Role' fields aren't equal");
        assertEquals(actualResp.getScreenName(), rCP.getScreenName(), "'ScreenName' fields aren't equal");

        Response listRQ = requestService.send();
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(listRQ);
        PlayersResponse actualListResp = listRQ.as(PlayersResponse.class);
        List<PlayerResponse> actualPlayers = actualListResp.getPlayers();

        PlayerResponse expectedPlayer = new PlayerResponse(
                actualResp.getId(),
                pCP.getAge(),
                rCP.getGender(),
                rCP.getRole(),
                rCP.getScreenName());

        assertTrue(actualPlayers.contains(expectedPlayer));
    }
}
