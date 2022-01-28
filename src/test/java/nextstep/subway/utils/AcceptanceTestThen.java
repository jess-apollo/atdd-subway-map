package nextstep.subway.utils;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.springframework.http.HttpStatus;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class AcceptanceTestThen {
    private static final String LOCATION_KEY_IN_HEADER = "Location";
    private static final String MESSAGE_PATH = "message";

    private final ExtractableResponse<Response> response;

    private AcceptanceTestThen(ExtractableResponse<Response> response) {
        this.response = response;
    }

    public static AcceptanceTestThen fromWhen(ExtractableResponse<Response> response) {
        return new AcceptanceTestThen(response);
    }

    public AcceptanceTestThen equalsHttpStatus(HttpStatus status) {
        assertThat(response.statusCode())
            .withFailMessage(
                FailMessage.NOT_MATCH_HTTP_STATUS.message(response.statusCode(), status.value())
            )
            .isEqualTo(status.value());
        return this;
    }

    public AcceptanceTestThen notExistsLocation() {
        assertThat(response.header(LOCATION_KEY_IN_HEADER))
            .withFailMessage(FailMessage.NOT_BLANK_LOCATION.message())
            .isBlank();
        return this;
    }

    public AcceptanceTestThen existsLocation() {
        assertThat(response.header(LOCATION_KEY_IN_HEADER))
            .withFailMessage(FailMessage.BLANK_LOCATION.message())
            .isNotBlank();
        return this;
    }

    public AcceptanceTestThen equalsResult(String jsonPath, String value) {
        String actual = response.jsonPath().get(jsonPath);

        assertThat(actual)
            .withFailMessage(
                FailMessage.NOT_MATCH_EXPERT.message(actual, value)
            )
            .isEqualTo(value);
        return this;
    }

    public <E> AcceptanceTestThen containsAll(String jsonPath, List<E> elements) {
        List<E> actual = response.jsonPath().get(jsonPath);

        assertThat(actual)
            .withFailMessage(
                FailMessage.NOT_MATCH_EXPERT.message(actual, elements)
            )
            .containsAll(elements);
        return this;
    }

    public <E> AcceptanceTestThen contains(String jsonPath, E elements) {
        List<E> actual = response.jsonPath().get(jsonPath);

        assertThat(actual)
            .withFailMessage(
                FailMessage.NOT_CONTAINS_EXPERT.message(actual, elements)
            )
            .contains(elements);
        return this;
    }

    public <E> AcceptanceTestThen checkEmpty(String jsonPath) {
        List<E> actual = response.jsonPath().get(jsonPath);

        assertThat(actual.size())
            .withFailMessage(
                FailMessage.NOT_EMPTY.message(actual)
            )
            .isEqualTo(0);
        return this;
    }

    public AcceptanceTestThen equalsMessage(String message) {
        String actual = response.jsonPath().get(MESSAGE_PATH);
        assertThat(actual)
            .withFailMessage(
                FailMessage.NOT_MATCH_MESSAGE.message(actual, message)
            )
            .isEqualTo(message);
        return this;
    }

    private enum FailMessage {
        NOT_MATCH_HTTP_STATUS("Http Status가 예상한 값과 다릅니다. \n결과 : %d\n예상 : %d"),
        NOT_BLANK_LOCATION("Location Header가 존재합니다."),
        BLANK_LOCATION("Location Header가 존재하지 않습니다."),
        NOT_MATCH_EXPERT("예상한 값이 다릅니다. \n결과 : %s\n예상 : %s"),
        NOT_CONTAINS_EXPERT("예상한 값이 포함되지 않았습니다. \n결과 : %s\n예상 : %s"),
        NOT_EMPTY("결과가 비어있지 않습니다. \n결과 : %s"),
        NOT_MATCH_MESSAGE("Message가 일치하지 않습니다. \n결과 : %s\n예상 : %s");

        FailMessage(String message) {
            this.message = message;
        }

        private final String message;

        public String message() {
            return message;
        }

        public String message(Object... objs) {
            return String.format(message, objs);
        }
    }
}