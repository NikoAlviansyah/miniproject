package com.miniproject.miniproject.services;

import com.miniproject.miniproject.dtos.customer.CreateCustomerDto;
import com.miniproject.miniproject.dtos.customer.DeleteCustomerReponseDto;
import com.miniproject.miniproject.dtos.customer.CustomerDto;
import com.miniproject.miniproject.dtos.customer.UpdateCustomerDto;
import com.miniproject.miniproject.exceptions.EntityNotFoundException;
import com.miniproject.miniproject.models.User;
import com.miniproject.miniproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CustomerService {

    private UserRepository userRepository;

    @Autowired
    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<CustomerDto> getAllCustomer() {
        List<CustomerDto> customerDtos = new ArrayList<>();

        Stream<User> customers = userRepository.getAllAdminOrCustomer("CUSTOMER", true).stream();

        customers.forEach(
                (customer) -> {
                    customerDtos.add(
                            new CustomerDto(
                                    customer.getUserId(),
                                    customer.getRole(),
                                    customer.getAccountNumber(),
                                    customer.getFund() == null ? 0 : customer.getFund(),
                                    customer.getFirstName(),
                                    customer.getLastName(),
                                    customer.getBirthDate(),
                                    customer.getGender(),
                                    customer.getPhone(),
                                    customer.getEmail(),
                                    customer.getAddress(),
                                    customer.getSubDistrict(),
                                    customer.getDistrict(),
                                    customer.getCity(),
                                    customer.getProvince(),
                                    customer.getZipCode()
                            )
                    );
                }
        );

        return customerDtos;
    }

    public CustomerDto createCustomer(CreateCustomerDto newCustomer) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String prefix = "C";
        Long idCounter = 1l;
        Boolean cek = false;

        String userId = String.format("%s%s", prefix, idCounter++);

        do {
            if (userRepository.findById(userId).isPresent()) {
                userId = String.format("%s%s", prefix, idCounter++);
            } else {
                cek = true;
            }
        } while (!cek);

        cek = false;

        String getYear = String.valueOf(LocalDate.now().getYear());
        Integer getMonth = LocalDate.now().getMonthValue();
        String str_getMonth = "";
        if (getMonth < 10) {
            str_getMonth = String.format("0%s", getMonth);
        } else {
            str_getMonth = String.format("%s", getMonth);
        }

        Integer suffix = 1;
        String str_suffix = String.format("000%s", suffix++);

        String accountNumber = String.format("%s%s%s", getYear, str_getMonth, str_suffix);

        do {
            if (userRepository.findCustomerByAccountNumber(accountNumber, true).isPresent()) {
                if (suffix < 10) {
                    str_suffix = String.format("000%s", suffix++);
                } else if (suffix < 100) {
                    str_suffix = String.format("00%s", suffix++);
                } else if (suffix < 1000) {
                    str_suffix = String.format("0%s", suffix++);
                } else {
                    str_suffix = String.format("%s", suffix++);
                }

                accountNumber = String.format("%s%s%s", getYear, str_getMonth, str_suffix);
            } else {
                cek = true;
            }
        } while (!cek);

        User customer = new User(userId,
                "Customer",
                true,
                accountNumber,
                newCustomer.getFund() == null ? 0 : newCustomer.getFund(),
                newCustomer.getFirstName(),
                newCustomer.getLastName(),
                LocalDate.parse(newCustomer.getBirthDate(), format),
                newCustomer.getGender(),
                newCustomer.getPhone(),
                newCustomer.getEmail(),
                newCustomer.getAddress(),
                newCustomer.getSubDistrict(),
                newCustomer.getDistrict(),
                newCustomer.getCity(),
                newCustomer.getProvince(),
                newCustomer.getZipCode()
        );

        userRepository.save(customer);

        return new CustomerDto(customer.getUserId(),
                customer.getRole(),
                customer.getAccountNumber(),
                customer.getFund() == null ? 0 : customer.getFund(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getGender(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getSubDistrict(),
                customer.getDistrict(),
                customer.getCity(),
                customer.getProvince(),
                customer.getZipCode());
    }

    public CustomerDto updateCustomer(UpdateCustomerDto oldCustomer) {
        User customer = userRepository.findById(oldCustomer.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
        customer.setPhone(oldCustomer.getPhone());
        customer.setEmail(oldCustomer.getEmail());
        customer.setAddress(oldCustomer.getAddress());
        customer.setSubDistrict(oldCustomer.getSubDistrict());
        customer.setDistrict(oldCustomer.getDistrict());
        customer.setCity(oldCustomer.getCity());
        customer.setProvince(oldCustomer.getProvince());
        customer.setZipCode(oldCustomer.getZipCode());

        userRepository.save(customer);

        return new CustomerDto(customer.getUserId(),
                customer.getRole(),
                customer.getAccountNumber(),
                customer.getFund() == null ? 0 : customer.getFund(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getBirthDate(),
                customer.getGender(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getSubDistrict(),
                customer.getDistrict(),
                customer.getCity(),
                customer.getProvince(),
                customer.getZipCode());
    }

    public DeleteCustomerReponseDto deleteCustomer(String userId) {
        User customer = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
        customer.setIsActive(false);
        userRepository.save(customer);
        return new DeleteCustomerReponseDto("Delete customer successfully.");
    }
}
