package com.miniproject.miniproject.dtos.customer;

import lombok.Data;

@Data
public class CreateCustomerDto {

    private final Long fund;
    private final String firstName;
    private final String lastName;
    private final String birthDate;
    private final String gender;
    private final String phone;
    private final String email;
    private final String address;
    private final String subDistrict;
    private final String district;
    private final String city;
    private final String province;
    private final String zipCode;
}
