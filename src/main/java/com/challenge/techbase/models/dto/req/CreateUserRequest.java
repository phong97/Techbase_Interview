package com.challenge.techbase.models.dto.req;

import com.challenge.techbase.models.entity.Profile;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.util.Enum.Gender;
import com.challenge.techbase.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {
    @Email(regexp = Constants.EMAIL_REGEX, message = "Email is invalid.")
    @NotBlank
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String dateOfBirth;
    @NotNull
    private Gender gender;
    @NotBlank
    private String phoneNumber;

    public User toModel() {
        Profile profile = new Profile();
        profile.setFirstName(this.firstName);
        profile.setLastName(this.lastName);
        profile.setDateOfBirth(this.dateOfBirth);
        profile.setGender(this.gender);
        profile.setPhoneNumber(this.phoneNumber);
        User user = new User();
        user.setEmail(this.email);
        user.setProfile(profile);
        return user;
    }
}
