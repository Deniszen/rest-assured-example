package assertion;

import io.restassured.response.Response;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public final class AssertCode {

    private AssertCode(){}

    public static void assertStatusCode(Response response, String operation, int code) {
        assertThat(format("%s received an answer with the wrong code",
                    operation), response.getStatusCode(), equalTo(code)
        );
    }
}