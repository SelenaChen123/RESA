package com.csc510.resa.rest.user.register;

import com.csc510.resa.document.UserDocument;
import com.csc510.resa.repository.UserRepository;
import com.csc510.resa.rest.user.User;
import com.csc510.resa.security.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for registering users
 */
@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public boolean register(User user) {
        UserDocument dbUser = userRepository.findByUsername(user.getUsername());

        if (dbUser != null)
            return false;

        if (user.getUsername() != null && user.getUsername().length() > 0 && user.getPassword() != null
                && user.getPassword().length() > 0) {

            int salt = (int) Math.round(Math.random() * 10000);
            String passHash = Hashing.hash(user.getPassword(), salt);
            userRepository.insert(new UserDocument(user.getUsername(), passHash, salt));
            return true;
        }

        return false;
    }
}
