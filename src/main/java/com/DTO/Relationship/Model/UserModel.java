package com.DTO.Relationship.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNullFields;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {
    @NotNull(message = "user name can not be Null")
    private String firstName;
    private String lastName;
    @Email(message = "Invalid Email format")
    private String email;
    @NotBlank(message="password can not be blank")
    @Size(min = 8, max = 15, message = "Password must at least 8 character")
    private String password;
    @NotBlank(message = "address can not be blank")
    private String address;
    @Min(value = 18, message = "age must be atleast 18")
    private int age;
    private String city;
    private String state;
    private String mobileNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dob;
}
