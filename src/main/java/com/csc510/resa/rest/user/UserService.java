package com.csc510.resa.rest.user;

import com.csc510.resa.document.UserDocument;
import com.csc510.resa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDocument user = userRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPasswordHash());
    }

}
