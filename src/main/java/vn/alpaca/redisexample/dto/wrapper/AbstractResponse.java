package vn.alpaca.redisexample.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class AbstractResponse {

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private Date timestamp;

    public AbstractResponse(int status) {
        this.status = status;
    }

    public AbstractResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
