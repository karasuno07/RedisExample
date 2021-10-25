package vn.alpaca.redisexample.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CustomerResponse implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private List<String> phoneNumbers;
    private String email;
}
