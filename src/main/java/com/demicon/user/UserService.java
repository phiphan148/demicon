package com.demicon.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

     private UserRepository userRepository;

    @Transactional
    public void processUser(List<User> users) {
        userRepository.saveAll(users);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findLastSaved() {
        return userRepository.findTopByOrderByIdDesc();
    }

}
