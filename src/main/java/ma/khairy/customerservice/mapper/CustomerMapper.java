package ma.khairy.customerservice.mapper;

import ma.khairy.customerservice.dto.CustomerRequest;
import ma.khairy.customerservice.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private ModelMapper modelMapper = new ModelMapper();

    Customer customer = new Customer();

    public Customer from(CustomerRequest customerRequest) {
        return modelMapper.map(customerRequest, Customer.class);
    }

}
