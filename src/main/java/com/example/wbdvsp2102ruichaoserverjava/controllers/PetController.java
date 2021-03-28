package com.example.wbdvsp2102ruichaoserverjava.controllers;

import com.example.wbdvsp2102ruichaoserverjava.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PetController {
    // register as an new user
    List<User> users = new ArrayList<User>();
    @GetMapping("/api/petfinder/register/{u}/{p}")
    public User register(
                        @PathVariable("u") String username,
                        @PathVariable("p") String password,
                         HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        session.setAttribute("currentUser", user);
        users.add(user);
        return user;
    }

    //retrieve profile
    @GetMapping("/api/petfinder/profile")
    public User profile(HttpSession session) {
        User currentUser = (User)
                session.getAttribute("currentUser");
        return currentUser;
    }

    //Logout
    @GetMapping("/api/petfinder/logout")
    public void logout
            (HttpSession session) {
        session.invalidate();
    }

    //Login passing credentials
    @GetMapping("/api/petfinder/login/{u}/{p}")
    public User login(	  @PathVariable("u") String username,
                          @PathVariable("p") String password,
                          HttpSession session) {
        for (User user : users) {
            if( user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                session.setAttribute("currentUser", user);
                return user;
            }
        }
        return null;
    }


    // store cookie
    @GetMapping("/api/petfinder/set/{attr}/{value}")
    public String setSessionAttribute(
            @PathVariable("attr") String attr,
            @PathVariable("value") String value,
            HttpSession session) {
        session.setAttribute(attr, value);
        return attr + " = " + value;
    }
    //retrieve cookie
    @GetMapping("/api/petfinder/get/{attr}")
    public String getSessionAttribute(
            @PathVariable ("attr") String attr,
            HttpSession session) {
        return (String)session.getAttribute(attr);
    }


    @GetMapping("/petfinder")
    public String sayHello(){
        return "Hello Cookie";
    }
}
