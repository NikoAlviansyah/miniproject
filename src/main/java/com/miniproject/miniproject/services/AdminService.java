package com.miniproject.miniproject.services;

import com.miniproject.miniproject.dtos.admin.AdminDto;
import com.miniproject.miniproject.dtos.admin.DeleteAdminResponseDto;
import com.miniproject.miniproject.dtos.admin.CreateAdminDto;
import com.miniproject.miniproject.dtos.admin.UpdateAdminDto;
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
public class AdminService {

    private UserRepository userRepository;

    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AdminDto> getAllAdmin() {
        List<AdminDto> adminDtos = new ArrayList<>();

        Stream<User> admins = userRepository.getAllAdminOrCustomer("ADMIN", true).stream();

        admins.forEach(
                (admin) -> {
                    adminDtos.add(
                            new AdminDto(
                                    admin.getUserId(),
                                    admin.getRole(),
                                    admin.getFirstName(),
                                    admin.getLastName(),
                                    admin.getBirthDate(),
                                    admin.getGender(),
                                    admin.getPhone(),
                                    admin.getEmail(),
                                    admin.getAddress(),
                                    admin.getSubDistrict(),
                                    admin.getDistrict(),
                                    admin.getCity(),
                                    admin.getProvince(),
                                    admin.getZipCode()
                            )
                    );
                }
        );

        return adminDtos;
    }

    public List<AdminDto> getAllAdminByName(String name) {
        List<AdminDto> adminDtos = new ArrayList<>();

        Stream<User> admins = userRepository.getAllAdminOrCustomerByName(name, "Admin", true).stream();

        admins.forEach(
                (admin) -> {
                    adminDtos.add(
                            new AdminDto(
                                    admin.getUserId(),
                                    admin.getRole(),
                                    admin.getFirstName(),
                                    admin.getLastName(),
                                    admin.getBirthDate(),
                                    admin.getGender(),
                                    admin.getPhone(),
                                    admin.getEmail(),
                                    admin.getAddress(),
                                    admin.getSubDistrict(),
                                    admin.getDistrict(),
                                    admin.getCity(),
                                    admin.getProvince(),
                                    admin.getZipCode()
                            )
                    );
                }
        );

        return adminDtos;
    }

    public List<AdminDto> getAllAdminByCity(String city) {
        List<AdminDto> adminDtos = new ArrayList<>();

        Stream<User> admins = userRepository.getAllAdminOrCustomerByCity(city, "Admin", true).stream();

        admins.forEach(
                (admin) -> {
                    adminDtos.add(
                            new AdminDto(
                                    admin.getUserId(),
                                    admin.getRole(),
                                    admin.getFirstName(),
                                    admin.getLastName(),
                                    admin.getBirthDate(),
                                    admin.getGender(),
                                    admin.getPhone(),
                                    admin.getEmail(),
                                    admin.getAddress(),
                                    admin.getSubDistrict(),
                                    admin.getDistrict(),
                                    admin.getCity(),
                                    admin.getProvince(),
                                    admin.getZipCode()
                            )
                    );
                }
        );

        return adminDtos;
    }

    public AdminDto createAdmin(CreateAdminDto newAdmin) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String prefix = "A";
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

        User admin = new User(userId,
                "Admin",
                true,
                null,
                null,
                newAdmin.getFirstName(),
                newAdmin.getLastName(),
                LocalDate.parse(newAdmin.getBirthDate(), format),
                newAdmin.getGender(),
                newAdmin.getPhone(),
                newAdmin.getEmail(),
                newAdmin.getAddress(),
                newAdmin.getSubDistrict(),
                newAdmin.getDistrict(),
                newAdmin.getCity(),
                newAdmin.getProvince(),
                newAdmin.getZipCode()
        );

        userRepository.save(admin);

        return new AdminDto(admin.getUserId(),
                admin.getRole(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getBirthDate(),
                admin.getGender(),
                admin.getPhone(),
                admin.getEmail(),
                admin.getAddress(),
                admin.getSubDistrict(),
                admin.getDistrict(),
                admin.getCity(),
                admin.getProvince(),
                admin.getZipCode());
    }

    public AdminDto updateAdmin(UpdateAdminDto oldAdmin) {
        User admin = userRepository.getAdminOrCustomerByUserId(oldAdmin.getUserId(), true)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found."));
        admin.setPhone(oldAdmin.getPhone());
        admin.setEmail(oldAdmin.getEmail());
        admin.setAddress(oldAdmin.getAddress());
        admin.setSubDistrict(oldAdmin.getSubDistrict());
        admin.setDistrict(oldAdmin.getDistrict());
        admin.setCity(oldAdmin.getCity());
        admin.setProvince(oldAdmin.getProvince());
        admin.setZipCode(oldAdmin.getZipCode());

        userRepository.save(admin);

        return new AdminDto(admin.getUserId(),
                admin.getRole(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getBirthDate(),
                admin.getGender(),
                admin.getPhone(),
                admin.getEmail(),
                admin.getAddress(),
                admin.getSubDistrict(),
                admin.getDistrict(),
                admin.getCity(),
                admin.getProvince(),
                admin.getZipCode());
    }

    public DeleteAdminResponseDto deleteAdmin(String userId) {
        User admin = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found."));
        admin.setIsActive(false);
        userRepository.save(admin);
        return new DeleteAdminResponseDto("Delete admin successfully.");
    }
}
