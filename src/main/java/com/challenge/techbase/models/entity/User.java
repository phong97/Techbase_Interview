package com.challenge.techbase.models.entity;

import com.challenge.techbase.util.Enum.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email
    private String email;
    @NotNull
    private String password;
    private Status status = Status.ACTIVE;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "team_user", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
    private Set<Team> teams;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Department> departments;

    public User() {
    }

    public User(int id, @Email String email, @NotNull String password, Set<Role> roles, Profile profile, Set<Team> teams) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.profile = profile;
        this.teams = teams;
    }
}