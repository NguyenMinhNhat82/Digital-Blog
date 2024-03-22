package com.nmn.controller;

import com.nmn.dto.UserDTO;
import com.nmn.model.Users;
import com.nmn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    UserService  userService;

    @PutMapping("/{id}/update")
    ResponseEntity<Users> updateProfile(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        return new ResponseEntity<>(userService.addOrUpdateProfile(userDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}/change-password")
    ResponseEntity<String> changePassword(@RequestBody Map<String,String> request, @PathVariable("id") Integer id){
        String password = request.get("password");
        if(userService.changePassword(id,password))
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
