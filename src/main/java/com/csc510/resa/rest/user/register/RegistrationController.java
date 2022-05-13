package com.csc510.resa.rest.user.register;

import com.csc510.resa.rest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for REST registration endpoints
 * 
 * @author Steven Green
 */
@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    /**
     * REST endpoint for user registration
     * 
     * @return Response Entity indicating if the login was successful or not
     */
    @PostMapping(value = "/user/register")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (registrationService.register(new User(username, password))) {
            response.sendRedirect("/login");
        } else {
            response.sendRedirect("/signup");
        }
    }
}
