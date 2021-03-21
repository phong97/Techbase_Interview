package com.challenge.techbase.models.entity;

import com.challenge.techbase.util.Enum.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String dateOfBirth;
    @NotNull
    private Gender gender;
    @NotNull
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "profile")
    private User user;

    public Profile() {
    }

    public Profile(int id, String firstName, String lastName, String dateOfBirth,
                   Gender gender, String phoneNumber, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

}
