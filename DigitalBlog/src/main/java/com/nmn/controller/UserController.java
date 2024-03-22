package com.nmn.controller;


import com.nmn.dto.UserDTO;
import com.nmn.model.Users;
import com.nmn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    ResponseEntity<Users> saveUser( @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.addOrUpdateProfile(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
        if (userService.deleteUser(id))
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/get-all")
    ResponseEntity<List<Users>> getAllListUser(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(userService.getListUser(params), HttpStatus.OK);
    }

    @PutMapping("/{id}/activate")
    ResponseEntity<String> activateUser(@PathVariable("id") Integer idUser){
        if (userService.activateUser(idUser))
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<>("SUCCESS", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping("/{id}/deactivate")
    ResponseEntity<String> deactivateUser(@PathVariable("id") Integer idUser){
        if (userService.deActivateUser(idUser))
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<>("SUCCESS", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
