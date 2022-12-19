package com.demicon.user;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

     private UserRepository userRepository;

    @Transactional
    public void processUsers(User user) {
        userRepository.saveAndFlush(user);
    }

}
