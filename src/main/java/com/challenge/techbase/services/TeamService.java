package com.challenge.techbase.services;

import com.challenge.techbase.models.entity.Team;

import java.util.Optional;

public interface TeamService {

    Team save(Team team);

    Team delete(Team team);

    Optional<Team> findById(Integer id);

}
