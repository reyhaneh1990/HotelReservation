package ir.reyhaneh.hotelreservation.service;

import ir.reyhaneh.hotelreservation.model.Customers;
import ir.reyhaneh.hotelreservation.repository.CustomerRepository;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

//    @Autowired
//    private CustomerRepository CustomerRepository;

    private final CustomerRepository customerRepository;

//    public CustomerServiceImpl(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    public List<Customers> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customers getCustomerById(Long id) {
        return customerRepository.getCustomer(id);
    }

    @Override
    public void saveCustomer(String name, String email, String phone, String address) {
        if (name.isEmpty()) {
            throw new ValidationException("Name is empty");
        }

        customerRepository.addCustomer(name, email, phone, address);
    }

    @Override
    public void updateCustomer(Long id, String name, String email, String phone, String address) {
        if (id == null) {
            throw new ValidationException("id is null");
        }
        if (getCustomerById(id) == null) {
            throw new ValidationException("Customer is not found");
        }

        customerRepository.editCustomer(id, name, email, phone, address);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (id == null) {
            throw new ValidationException("id is null");
        }
        if (getCustomerById(id) == null) {
            throw new ValidationException("Customer is not found");
        }

        customerRepository.deleteCustomer(id);
    }
}
