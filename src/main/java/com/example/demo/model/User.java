package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

@Setter
@Getter
@ToString
@Builder
public class User extends ResourceSupport {
    private long userId;
    private String name;
    private String email;

}
