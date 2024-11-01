package com.backendkiss.backendkiss.controllers.user;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.backendkiss.backendkiss.entity.Characters;
import com.backendkiss.backendkiss.entity.User;
import com.backendkiss.backendkiss.resolver.UserQueryResolver;

@Controller
public class UserGraphQLController {

    private final UserQueryResolver userQueryResolver;

    public UserGraphQLController(UserQueryResolver userQueryResolver) {
        this.userQueryResolver = userQueryResolver;
    }

    @QueryMapping()
    public User getUserById(@Argument int id) {
        return userQueryResolver.getUserById(id);
    }

    @QueryMapping()
    public List<User> getAllUsers() {
        return userQueryResolver.getAllUsers();
    }
}
