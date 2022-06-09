package com.miniproject.miniproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "[User]")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @Column(name = "UserID")
    private String userId;

    @Column(name = "Role")
    private String role;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "AccountNumber")
    private String accountNumber;

    @Column(name = "Fund")
    private Long fund;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "SubDistrict")
    private String subDistrict;

    @Column(name = "District")
    private String district;

    @Column(name = "City")
    private String city;

    @Column(name = "Province")
    private String province;

    @Column(name = "ZipCode")
    private String zipCode;
}
