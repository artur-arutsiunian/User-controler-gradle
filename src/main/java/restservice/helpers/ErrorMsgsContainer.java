package restservice.helpers;

public class ErrorMsgsContainer {

    public static final String WERE_NOT_EQUALS = "expected %s and actual weren't equals;";
    public static final String STATUS_CODES = String.format(WERE_NOT_EQUALS, "status code");
    public static final String CONTENT_TYPES = String.format(WERE_NOT_EQUALS, "content type");
}
