package com.demicon.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/import-users")
    public List<User> importUsers() {
        String uri = "https://randomuser.me/api?inc=gender,location,email,login&results=20";

        RestTemplate restTemplate = new RestTemplate();

        try {
            restTemplate.getForObject(uri, String.class);

            String result = restTemplate.getForObject(uri, String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            TypeReference<HashMap<String, List<User>>> typeReference = new TypeReference<>(){};

            HashMap<String, List<User>> results= mapper.readValue(result, typeReference);
            List<User> users = results.get("results");

            userService.processUser(users);

            if (users != null) {
                return users;
            } else {
                return userService.findLastSaved();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getUsers")
    public Map<String, Object> getUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = users.stream().map(userMapper::toDto).collect(Collectors.toList());

        Map<String, List<UserDto>> userGroupByCountry = userDtos.stream()
                        .collect(Collectors.groupingBy(UserDto::getCountry, Collectors.toList()));

        Map<String, Object> result = new HashMap<>();
        result.put("countries", getUserMapByCountryList(userGroupByCountry));

        return result;
    }

    private List<Map<String, Object>> getUserMapByCountryList(Map<String, List<UserDto>> userGroupByCountry) {
        List<Map<String, Object>> userMapByCountryList = new ArrayList<>();
        for(Map.Entry<String, List<UserDto>> entry : userGroupByCountry.entrySet()) {
            Map<String, Object> userMapByCountry = new HashMap<>();
            userMapByCountry.put("name", entry.getKey());
            userMapByCountry.put("users", entry.getValue());
            userMapByCountryList.add(userMapByCountry);
        }
        return userMapByCountryList;
    }

}
