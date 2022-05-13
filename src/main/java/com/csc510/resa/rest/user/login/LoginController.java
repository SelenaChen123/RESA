package com.csc510.resa.rest.user.login;

import com.csc510.resa.rest.user.JwtService;
import com.csc510.resa.rest.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for REST login endpoints
 * 
 * @author Steven Green
 */
@RestController
public class LoginController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private com.csc510.resa.rest.user.login.LoginService loginService;

    /**
     * REST endpoint for user authentication If the login was successful, a JWT
     * token will be set as a cookie.
     */
    @PostMapping(value = "/user/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        if (loginService.login(user)) {
            Cookie cookie = new Cookie("token", jwtService.generate(user));
            cookie.setMaxAge(-1);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/login");
        }
    }

    /**
     * REST endpoint for logging out.
     */
    @GetMapping(value = "/user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/login");
    }

}
