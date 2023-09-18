package restservicetest.EditTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userGet.response.PlayerResponse;
import restservice.pojo.userGet.response.PlayersResponse;
import restservice.pojo.userPatch.request.PatchRequest;
import restservice.pojo.userPatch.response.PatchResponse;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class EditUserTest {

    private RequestService requestService = RequestService.getInstance();

    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("user5")
            .buildPassword("1234567")
            .buildScreenName("Use3")
            .build();

    @Test(description = "Change 'user' age by user")
    @Description("Change 'user' age by user")
    public void editUserAgeByUserPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("user")
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .buildPlayerAge(25)
                .build();
        Response respPS = requestService.send(pCP, "user/", id);
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respPS);
        PatchResponse actualResp = respPS.as(PatchResponse.class);
        Assert.assertEquals(actualResp.getAge(), pCP.getAge(), "'Age' fields aren't equal");
        Assert.assertEquals(actualResp.getGender(), rCP.getGender(), "'Gender' fields aren't equal");
        Assert.assertEquals(actualResp.getLogin(), rCP.getLogin(), "'Login' fields aren't equal");
        Assert.assertEquals(actualResp.getRole(), rCP.getRole(), "'Role' fields aren't equal");
        Assert.assertEquals(actualResp.getScreenName(), rCP.getScreenName(), "'ScreenName' fields aren't equal");

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

    @Test(description = "Change 'admin' age by user'")
    @Description("Change 'admin' age by user'")
    public void editAdminAgeByUserNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Map<String, Object> requestMap = rCP.toMap();
        Response respCP = requestService.send(requestMap, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .buildPlayerAge(25)
                .build();
        Response respPS = requestService.send(pCP, "user/", id);
        AssertionsHelper.assertStatusCodeForbiddenNegative(respPS);

        String responseBody = respPS.asString();
        Assert.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }
}
