package vn.alpaca.redisexample.dto.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<S> extends AbstractResponse {

    @JsonProperty("data")
    private final S data;

    public SuccessResponse(S data) {
        super(200);
        this.data = data;
    }
}
