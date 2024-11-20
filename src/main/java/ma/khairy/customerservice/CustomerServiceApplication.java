package ma.khairy.customerservice;

import ma.khairy.customerservice.entities.Customer;
import ma.khairy.customerservice.repository.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepo customerRepo) {
        return args -> {
            customerRepo.save(Customer.builder().name("khaled").email("k@k.com").build());
            customerRepo.save(Customer.builder().name("mohamed").email("m@m.com").build());
            customerRepo.save(Customer.builder().name("ahmed").email("a@a.com").build());
        };
    }
}
