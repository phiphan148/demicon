package com.demicon.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneOffset;
import java.util.*;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserRestController {

    private static final ParameterizedTypeReference<List<User>> RESPONSE_TYPE = new ParameterizedTypeReference<>() {};

    private final UserService userService;

    @GetMapping("/import-users")
    public List<User> importUsers() throws JsonProcessingException {
        String uri = "https://randomuser.me/api?inc=gender,name,location,email";
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        TypeReference<HashMap<String, List<User>>> typeReference = new TypeReference<>(){};

        HashMap<String, List<User>> results= mapper.readValue(result, typeReference);

        List<User> users = results.get("results");

        val user = User.builder().id(123).build();

        userService.processUser(user);

      /*  System.out.println("User New" + user);

        users.stream().forEach(el -> BeanUtils.copyProperties(el, user));

        users.stream().forEach(userService::processUser);*/

        System.out.println("Users" + users);

        return users;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return  userService.findAll();
    }

}
