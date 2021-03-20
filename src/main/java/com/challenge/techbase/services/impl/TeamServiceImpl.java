package com.challenge.techbase.services.impl;

import com.challenge.techbase.mappers.TeamRepository;
import com.challenge.techbase.models.entity.Team;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.TeamService;
import com.challenge.techbase.util.Enum.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepo;

    @Override
    public Team save(Team team) {
        return this.teamRepo.saveAndFlush(team);
    }

    @Override
    public Team delete(Team team) {
        team.setStatus(Status.DELETED);
        return this.teamRepo.saveAndFlush(team);
    }

    @Override
    public Optional<Team> findById(Integer id) {
        return this.teamRepo.findById(id);
    }

}
