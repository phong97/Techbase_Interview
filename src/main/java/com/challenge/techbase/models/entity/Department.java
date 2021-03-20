package com.challenge.techbase.models.entity;

import com.challenge.techbase.util.Enum.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private Status status = Status.ACTIVE;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Team> teams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User user;

    public Department() {
    }

    public Department(int id, String name, Status status, List<Team> teams, User user) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.teams = teams;
        this.user = user;
    }
}
