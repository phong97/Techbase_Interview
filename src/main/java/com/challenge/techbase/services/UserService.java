package com.challenge.techbase.services;

import com.challenge.techbase.models.dto.paging.PagingDto;
import com.challenge.techbase.models.dto.paging.PagingParams;
import com.challenge.techbase.models.entity.Team;
import com.challenge.techbase.models.entity.User;

import java.util.Optional;

public interface UserService {

    boolean checkLogin(User user, String password);

    User save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(int id);

    void addMember(Team team, User user);

    void removeMember(Team team, User user);

    PagingDto findAll(PagingParams pagingParams);
}
