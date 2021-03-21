package com.challenge.techbase.controllers;

import com.challenge.techbase.exceptions.RestException;
import com.challenge.techbase.models.dto.paging.PagingDto;
import com.challenge.techbase.models.dto.paging.PagingParams;
import com.challenge.techbase.models.dto.UserDetailDto;
import com.challenge.techbase.models.dto.req.FindAllUserRequest;
import com.challenge.techbase.models.dto.req.RegisterReq;
import com.challenge.techbase.models.dto.res.FindAllUserResponse;
import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/{email}")
    public ResponseEntity<UserDetailDto> findByEmail(@PathVariable("email") String email) {
        Optional<User> userOptional = this.userService.findByEmail(email);
        if (!userOptional.isPresent()) {
            logger.error("User not found");
            throw new RestException("User not found");
        }

        User user = userOptional.get();
        UserDetailDto userDetailDto = new UserDetailDto(user);
        return ResponseEntity.ok(userDetailDto);
    }

    @Secured({"ROLE_CEO"})
    @PostMapping
    public ResponseEntity<FindAllUserResponse> findAllUser(@Valid @RequestBody FindAllUserRequest req) {
        PagingParams pagingParams = new PagingParams(req.getOffset(), req.getSize(), req.getSortColumn(), req.getSortDirection());
        PagingDto<User> users = this.userService.findAll(pagingParams);
        List<UserDetailDto> userDetailDtos = users.getData()
                .stream()
                .map(user -> new UserDetailDto(user))
                .collect(Collectors.toList());
        FindAllUserResponse response = new FindAllUserResponse(users.getTotal(), userDetailDtos);
        return ResponseEntity.ok(response);
    }

    @Secured({"ROLE_CEO"})
    @PostMapping("/register")
    public ResponseEntity<UserDetailDto> register(@Valid @RequestBody RegisterReq req) {
        Optional<User> userOptional = this.userService.findByEmail(req.getEmail());
        if (userOptional.isPresent()) {
            logger.error("Email [{}] has been used.", req.getEmail());
            throw new RestException("User already exists.");
        }

        String password = this.bCryptPasswordEncoder.encode(req.getPassword());
        User user = req.toModel(password);
        user = this.userService.save(user);
        UserDetailDto res = new UserDetailDto(user);
        return ResponseEntity.ok(res);
    }
}
