package ir.reyhaneh.hotelreservation.service;

import ir.reyhaneh.hotelreservation.model.Customers;

import java.util.List;

public interface CustomerService {

    List<Customers> getAllCustomers();

    Customers getCustomerById(Long id);

    void saveCustomer(String name, String email, String phone, String address);

    void updateCustomer(Long id, String name, String email, String phone, String address);

    void deleteCustomer(Long id);

}
