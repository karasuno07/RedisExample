package vn.alpaca.redisexample.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vn.alpaca.redisexample.dto.request.CustomerRequest;
import vn.alpaca.redisexample.dto.response.CustomerResponse;
import vn.alpaca.redisexample.entity.Customer;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerResponse convertToResponseModel(Customer customer);

    Customer convertToEntity(CustomerRequest requestData);
}
