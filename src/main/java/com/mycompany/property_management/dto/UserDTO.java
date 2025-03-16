package com.mycompany.property_management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String ownerName;

    @NotNull(message = "Owner email is Mandatory")
    @Size(min=1, max =50, message = "Owner email should be between 1 to 50 character in length")
    @NotEmpty(message = "Owner email cannot be empty")
    private String ownerEmail;
    private String phoneNumber;
    @NotNull(message = "Password is Mandatory")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
