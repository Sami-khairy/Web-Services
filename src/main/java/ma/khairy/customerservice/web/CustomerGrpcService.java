package ma.khairy.customerservice.web;

import io.grpc.stub.StreamObserver;
import ma.khairy.customerservice.entities.Customer;
import ma.khairy.customerservice.mapper.CustomerMapper;
import ma.khairy.customerservice.repository.CustomerRepo;
import ma.khairy.customerservice.stub.CustomerServiceGrpc;
import ma.khairy.customerservice.stub.CustomerServiceOuterClass;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetAllCustomersResponse> responseObserver) {
        List<Customer> customerList = customerRepo.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustumers = customerList.stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
        List<CustomerServiceOuterClass.Customer> customers = new ArrayList<>();
        CustomerServiceOuterClass.GetAllCustomersResponse customersResponse =
                CustomerServiceOuterClass.GetAllCustomersResponse
                        .newBuilder()
                        .addAllCustomers(grpcCustumers)
                        .build();
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customer = customerRepo.findById(request.getId()).get();
        CustomerServiceOuterClass.GetCustomerByIdResponse customersResponse =
                CustomerServiceOuterClass.GetCustomerByIdResponse .newBuilder()
                        .setCustomer(customerMapper.fromCustomer(customer))
                        .build();

        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        CustomerServiceOuterClass.CustomerRequest customerRequest = request.getCustomer();
        Customer customer = customerMapper.fromGrpcCustomer(customerRequest);
        Customer savedCustomer = customerRepo.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse customerResponse =
                CustomerServiceOuterClass.SaveCustomerResponse
                        .newBuilder()
                        .setCustomer(customerMapper.fromCustomer(savedCustomer))
                        .build();

        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }
}
