package ma.khairy.customerservice.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.khairy.customerservice.dto.CustomerRequest;
import ma.khairy.customerservice.entities.Customer;
import ma.khairy.customerservice.mapper.CustomerMapper;
import ma.khairy.customerservice.repository.CustomerRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@WebService(serviceName = "CustomerWS")
public class CustomerSoapController {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;


    @WebMethod
    public List<Customer> customerList() {
        return customerRepo.findAll();
    }
    @WebMethod
    public Customer customerById(@WebParam(name = "id") Long id) {
        return customerRepo.findById(id).get();
    }

    @WebMethod
    public Customer saveCustomer(@WebParam CustomerRequest customerRequest) {
        return customerRepo.save(customerMapper.from(customerRequest));
    }
}
