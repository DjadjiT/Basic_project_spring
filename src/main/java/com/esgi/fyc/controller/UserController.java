package com.esgi.fyc.controller;

import com.esgi.fyc.exception.UserException;
import com.esgi.fyc.model.Users;
import com.esgi.fyc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @DeleteMapping
    public ResponseEntity<?> deleteUser(Principal principal) {
        if (principal == null)
            return ResponseEntity.badRequest().body("User doesn't exist !");
        Users user;
        try {
            user = userService.findByUsername(principal.getName());
            userService.delete(user);
            return ResponseEntity.ok("User deleted  successfully !");
        } catch (UserException e) {
            return ResponseEntity.notFound().build();

        }
    }



}
