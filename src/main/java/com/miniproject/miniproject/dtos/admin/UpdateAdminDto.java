package com.miniproject.miniproject.dtos.admin;

import lombok.Data;

@Data
public class UpdateAdminDto {

    private final String userId;
    private final String phone;
    private final String email;
    private final String address;
    private final String subDistrict;
    private final String district;
    private final String city;
    private final String province;
    private final String zipCode;

}
