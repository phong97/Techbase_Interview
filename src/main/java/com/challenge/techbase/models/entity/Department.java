package com.challenge.techbase.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Team> teams;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User user;

    public Department() {
    }

    public Department(int id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

}
