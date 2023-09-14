package restservice.helpers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class AssertionsHelper {

    public static void assertStatusCodeOKAndContentTypeOK(Response r) {
        assertStatusCodeAndContentType(r, HttpStatus.SC_OK, ContentType.JSON);
    }

    public static void assertStatusCodeAndContentType(Response r, int code, ContentType type) {
        Assert.assertEquals(r.statusCode(), code, ErrorMsgsContainer.STATUS_CODES);
        Assert.assertEquals(type.toString(), r.contentType(), ErrorMsgsContainer.CONTENT_TYPES);
    }

    public static void assertStatusCodeBadRequestNegative(Response r) {
        assertStatusCodeBadRequestNegative(r, HttpStatus.SC_BAD_REQUEST);
    }

    public static void assertStatusCodeBadRequestNegative(Response r, int code) {
        Assert.assertEquals(r.statusCode(), code, ErrorMsgsContainer.STATUS_CODES);
    }

    public static void assertStatusCodeForbiddenNegative(Response r) {
        assertStatusCodeForbiddenNegative(r, HttpStatus.SC_FORBIDDEN);
    }

    public static void assertStatusCodeForbiddenNegative(Response r, int code) {
        Assert.assertEquals(r.statusCode(), code, ErrorMsgsContainer.STATUS_CODES);
    }

    public static void assertStatusCodeNoContent(Response r) {
        assertStatusCodeNoContent(r, HttpStatus.SC_NO_CONTENT);
    }

    public static void assertStatusCodeNoContent(Response r, int code) {
        Assert.assertEquals(r.statusCode(), code, ErrorMsgsContainer.STATUS_CODES);
    }
}