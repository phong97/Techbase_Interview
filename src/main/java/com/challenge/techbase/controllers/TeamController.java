package com.challenge.techbase.controllers;

import com.challenge.techbase.exceptions.RestException;
import com.challenge.techbase.models.dto.DepartmentDto;
import com.challenge.techbase.models.dto.TeamDto;
import com.challenge.techbase.models.dto.req.AddMemberRequest;
import com.challenge.techbase.models.dto.req.CreateTeamRequest;
import com.challenge.techbase.models.dto.req.RemoveMemberRequest;
import com.challenge.techbase.models.dto.req.UpdateTeamRequest;
import com.challenge.techbase.models.entity.Department;
import com.challenge.techbase.models.entity.Team;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.DepartmentService;
import com.challenge.techbase.services.TeamService;
import com.challenge.techbase.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/team")
public class TeamController {
    private static final Logger logger = LoggerFactory.getLogger(Team.class);

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> findTeamById(@PathVariable("id") Integer teamId) {
        Optional<Team> teamOptional = this.teamService.findById(teamId);
        if (!teamOptional.isPresent()) {
            logger.error("Team not found by id");
            throw new RestException("Team not found by id");
        }

        Team team = teamOptional.get();
        TeamDto teamDto = new TeamDto(team);
        return ResponseEntity.ok(teamDto);
    }

    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@Valid @RequestBody CreateTeamRequest req) {
        Team team = req.toModel();
        Team newTeam = this.teamService.save(team);
        TeamDto teamDto = new TeamDto(newTeam);
        return ResponseEntity.ok(teamDto);
    }

    @PutMapping
    public ResponseEntity<TeamDto> updateTeam(@Valid @RequestBody UpdateTeamRequest req) {
        Integer teamId = req.getId();
        Optional<Team> teamOptional = this.teamService.findById(teamId);
        if (!teamOptional.isPresent()) {
            logger.error("Team not found by id");
            throw new RestException("Team not found by id");
        }

        Team team = teamOptional.get();
        Team newTeam = req.toModel(team);
        newTeam = this.teamService.save(newTeam);
        TeamDto teamDto = new TeamDto(newTeam);
        return ResponseEntity.ok(teamDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamDto> deleteTeam(@PathVariable("id") Integer teamId){
        Optional<Team> teamOptional = this.teamService.findById(teamId);
        if (!teamOptional.isPresent()) {
            logger.error("Team not found by id");
            throw new RestException("Team not found by id");
        }

        Team team = teamOptional.get();
        team = this.teamService.delete(team);
        TeamDto teamDto = new TeamDto(team);
        return ResponseEntity.ok(teamDto);
    }

    @PostMapping("/add_member")
    public ResponseEntity<Void> addMember(@Valid @RequestBody AddMemberRequest req){
        Integer teamId = req.getTeamId();
        Integer userId = req.getUserId();

        Optional<Team> teamOptional = this.teamService.findById(teamId);
        if (!teamOptional.isPresent()) {
            logger.info("Team not found by id");
            throw new RestException("Team not found by id");
        }

        Optional<User> userOptional = this.userService.findById(userId);
        if (!userOptional.isPresent()) {
            logger.info("User not found by id");
            throw new RestException("User not found by id");
        }

        Team team = teamOptional.get();
        User user = userOptional.get();
        this.userService.addMember(team, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove_member")
    public ResponseEntity<Void> removeMember(@Valid @RequestBody RemoveMemberRequest req){
        Integer teamId = req.getTeamId();
        Integer userId = req.getUserId();

        Optional<Team> teamOptional = this.teamService.findById(teamId);
        if (!teamOptional.isPresent()) {
            logger.info("Team not found by id");
            throw new RestException("Team not found by id");
        }

        Optional<User> userOptional = this.userService.findById(userId);
        if (!userOptional.isPresent()) {
            logger.info("User not found by id");
            throw new RestException("User not found by id");
        }

        Team team = teamOptional.get();
        User user = userOptional.get();
        this.userService.removeMember(team, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
