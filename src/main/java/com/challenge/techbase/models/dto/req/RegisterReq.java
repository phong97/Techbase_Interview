package com.challenge.techbase.models.dto.req;

import com.challenge.techbase.models.entity.Profile;
import com.challenge.techbase.models.entity.Role;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.util.Constants;
import com.challenge.techbase.util.Enum.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;

@Getter
@Setter
public class RegisterReq {
    @Email(regexp = Constants.EMAIL_REGEX, message = "Email is invalid.")
    @NotBlank(message = "Email is required.")
    private String email;
    @NotBlank(message = "Password is required.")
    private String password;
    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;
    @NotNull(message = "Gender is required.")
    private Gender gender;
    @NotNull(message = "Date of birth is required.")
    private String dateOfBirth;
    @NotNull(message = "Role id is required.")
    private RoleId role;

    public RegisterReq() {
    }

    public RoleId getRole() {
        return role == null ? RoleId.MEMBER : role;
    }

    public void setRole(RoleId role) {
        this.role = role;
    }

    public User toModel(String password) {
        Profile profile = new Profile();
        profile.setFirstName(this.firstName);
        profile.setLastName(this.lastName);
        profile.setGender(this.gender);
        profile.setPhoneNumber(this.phoneNumber);
        profile.setDateOfBirth(this.dateOfBirth);

        User user = new User();
        user.setEmail(this.email);
        user.setPassword(password);
        user.setProfile(profile);
        user.setRoles(Collections.singleton(new Role(this.getRole().id)));
        return user;
    }

}
