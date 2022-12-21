package com.demicon.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Login {
    @JsonProperty("username")
    private String name;
}
