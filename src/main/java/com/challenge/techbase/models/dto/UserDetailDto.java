package com.challenge.techbase.models.dto;

import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.util.Enum.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDetailDto {
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender;
    private String phoneNumber;

    public UserDetailDto(User user) {
        this.email = user.getEmail();
        this.firstName = user.getProfile().getFirstName();
        this.lastName = user.getProfile().getLastName();
        this.dateOfBirth = user.getProfile().getDateOfBirth();
        this.gender = user.getProfile().getGender();
        this.phoneNumber = user.getProfile().getPhoneNumber();
    }
}
