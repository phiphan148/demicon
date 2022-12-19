package com.demicon.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@RestController
public class UserRestController {

    private static final ParameterizedTypeReference<List<String>> RESPONSE_TYPE_INT_LIST = new ParameterizedTypeReference<>() {};

    private UserService userService;

    @GetMapping("/users")
    public String getUsers() {
        String uri = "https://randomuser.me/api";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        User user = User.builder().id(123L).name(result).build();
        userService.processUsers(user);
        return result;

       /* ResponseEntity<List<String>> response = restTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                RESPONSE_TYPE_INT_LIST);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        } else {
            log.warn("get erp numbers not successful with api response: " + response.getStatusCode());
            return Collections.emptyList();
        }*/

//        ResponseEntity<List<User>> exchange = restTemplate
//                .exchange(uri,
//                        HttpMethod.GET,
//                        null,
//                        RESPONSE_TYPE);
//
//        User user = new User();
//
//        if (exchange.getStatusCode() == HttpStatus.OK && exchange.getBody() != null) {
//            user = (User) exchange.getBody();
//        }
    }

}
