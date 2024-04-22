package com.trainReservation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @Size(min=4, message="User ID should be minimum of 4 characters.")
    @Column(name = "user_id")
    private String userId;

    @Size(min=4, message="Password should be minimum of 4 characters.")
    @Column(name = "password", nullable = false)
    private String password;

    @Size(min=1, message="Full Name is required.")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Min(value=1, message="Age should be positive.")
    @Column(name = "age", nullable = false)
    private int age;

    @NotNull(message="Select your Gender.")
    @Column(name = "gender", nullable = false)
    private String gender;

    @Pattern(regexp = "[6-9]{1}[0-9]{9}", message="Phone Number should be valid with 10 digits.")
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Size(min=1, message="Address is required.")
    @Column(name = "address")
    private String address;

}

