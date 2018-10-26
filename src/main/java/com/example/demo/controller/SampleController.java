package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class SampleController {

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable long userId) {
        User user = getUser(userId);
        user.add(linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUserById(userId)).withRel("target_url"));
        user.add(linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteUserById(userId)).withRel("remove_url"));
        return user;
    }

    @GetMapping("/user/remove/{userId}")
    public Map<String, String> deleteUserById(@PathVariable long userId) {
        deleteUser(userId);
        return new HashMap<>();
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>(setUser().values());
        for (User user : users) {
            user.add(linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUserById(user.getUserId())).withRel("target_url"));
            user.add(linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteUserById(user.getUserId())).withRel("remove_url"));
        }
        return users;
    }


    private User getUser(long userId) {
        return setUser().get(userId);
    }

    private void deleteUser(long userId) {
        setUser().remove(userId);
    }

    private Map<Long, User> setUser() {
        Map<Long, User> userMap = new HashMap<>();
        userMap.put(0L, User.builder().name("yyyy").email("yyyy@gmail.com").userId(0).build());
        userMap.put(1L, User.builder().name("bbbb").email("bbbb@gmail.com").userId(1).build());
        userMap.put(2L, User.builder().name("cccc").email("cccc@gmail.com").userId(2).build());
        userMap.put(3L, User.builder().name("aaaa").email("aaaa@gmail.com").userId(3).build());
        return userMap;
    }
}
