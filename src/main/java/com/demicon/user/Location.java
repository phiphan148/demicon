package com.demicon.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Location {
    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonStringType")
    @JsonIgnore
    private Object street;
    private String city;
    private String state;
    private String country;

    @JsonIgnore
    private String postcode;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonStringType")
    @JsonIgnore
    private Object coordinates;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonStringType")
    @JsonIgnore
    private Object timezone;
}
