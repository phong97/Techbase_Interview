package com.challenge.techbase.services.impl;

import com.challenge.techbase.mappers.UserRepository;
import com.challenge.techbase.models.dto.paging.PagingDto;
import com.challenge.techbase.models.dto.paging.PagingParams;
import com.challenge.techbase.models.entity.Team;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.UserService;
import com.challenge.techbase.util.Enum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean checkLogin(User user, String password) {
        return bCryptPasswordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User save(User user) {
        return this.userRepo.saveAndFlush(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> findById(int id) {
        return this.userRepo.findById(id);
    }

    @Override
    public void addMember(Team team, User user) {
        Set<Team> teams = user.getTeams();
        teams.add(team);
        user.setTeams(teams);
        this.userRepo.saveAndFlush(user);
    }

    @Override
    public void removeMember(Team team, User user) {
        Set<Team> teams = user.getTeams();
        teams.remove(team);
        user.setTeams(teams);
        this.userRepo.saveAndFlush(user);
    }

    @Override
    public PagingDto findAll(PagingParams pagingParams) {
        String sortColumn = this.getSortColumn(pagingParams.getSortColumn());
        Sort sort = Sort.by(sortColumn);
        sort = pagingParams.getDirection() == Enum.SortDirection.ASC ?
                sort.ascending() : sort.descending();

        PageRequest pageRequest = PageRequest.of(pagingParams.getOffset(), pagingParams.getSize(), sort);
        Page<User> users = this.userRepo.findAll(pageRequest);
        Long totalElement = users.getTotalElements();
        PagingDto<User> pagingDto = new PagingDto<>(totalElement, users.getContent());
        return pagingDto;
    }

    private String getSortColumn(String sortColumn) {
        String columnName = "email";
        if (sortColumn.isEmpty() || !sortColumn.equals("email")) {
            columnName = "profile." + sortColumn;
        }

        return columnName;
    }
}
