package com.challenge.techbase.models.dto.req;

import com.challenge.techbase.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @Email(regexp = Constants.EMAIL_REGEX, message = "Email is invalid.")
    @NotBlank(message = "Email is mandatory.")
    private String email;
    @NotBlank
    private String password;
}
