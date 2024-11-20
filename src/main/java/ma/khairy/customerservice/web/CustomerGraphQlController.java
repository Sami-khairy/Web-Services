package ma.khairy.customerservice.web;

import lombok.RequiredArgsConstructor;
import ma.khairy.customerservice.dto.CustomerRequest;
import ma.khairy.customerservice.entities.Customer;
import ma.khairy.customerservice.mapper.CustomerMapper;
import ma.khairy.customerservice.repository.CustomerRepo;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerGraphQlController {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;


    @QueryMapping
    public List<Customer> allCustomers() {
        return customerRepo.findAll();
    }

    @QueryMapping
    public Customer customerById(@Argument Long id) {
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with id : " + id));
    }

    @MutationMapping
    public Customer saveCustomer(@Argument CustomerRequest customer) {
        Customer c = customerMapper.from(customer);
        return customerRepo.save(c);
    }
}
