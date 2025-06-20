package com.contactly.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString 
public class UserForm {

    @NotBlank(message = "Name is required")
    @Size(min=2, message = "Min 3 character is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6 , message = "Min 6 character is required")
    private String password;

    @NotBlank(message = "about is required")
    private String about;
    @Size(min=8 , max=12 , message = "Phone number must be between 8 and 12 characters")
    private String phoneNumber;



}
