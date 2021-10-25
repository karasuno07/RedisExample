package vn.alpaca.redisexample.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    @JsonProperty("page")
    private int pageNumber;

    @JsonProperty("limit")
    private int pageSize;

    @JsonProperty("sort-by")
    private String sortCriteria;

    @JsonProperty("asc")
    private boolean direction = true;
}
