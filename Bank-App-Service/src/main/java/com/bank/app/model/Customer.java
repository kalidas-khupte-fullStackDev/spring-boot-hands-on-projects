package com.bank.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity(name = "Customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First Name can't be Empty")
    private String firstName;

    @NotEmpty(message = "Last Name can't be Empty")
    private String lastName;

    @NotEmpty(message = "Email can't be Empty")
    @Email
    private String email;

    @Size(max = 12)
    private String phoneNumber;
}
