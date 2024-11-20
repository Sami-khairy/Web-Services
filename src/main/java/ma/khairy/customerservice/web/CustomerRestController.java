package ma.khairy.customerservice.web;

import lombok.RequiredArgsConstructor;
import ma.khairy.customerservice.entities.Customer;
import ma.khairy.customerservice.repository.CustomerRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerRepo customerRepo;


    @GetMapping("/customers")
    public List<Customer> customerList() {
        return customerRepo.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable Long id) {
        return customerRepo.findById(id).get();
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerRepo.save(customer);
    }

}
