package vn.alpaca.redisexample.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorResponse extends AbstractResponse {

    @JsonProperty("errors")
    private Map<String, String> errors;

    public ErrorResponse(
            int status,
            String message,
            Map<String, String> errors
    ) {
        super(status, message);
        this.errors = errors;
    }
}
