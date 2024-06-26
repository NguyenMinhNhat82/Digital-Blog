package com.nmn.controller;


import com.nmn.dto.UserDTO;
import com.nmn.model.Users;
import com.nmn.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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


    @Operation(summary = "Save user", description = "Save user")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/save")
    ResponseEntity<Users> saveUser( @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.addOrUpdateProfile(userDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete user", description = "Delete user")
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}/delete")
    ResponseEntity<String> deleteUser(@PathVariable("id") Integer id){
        if (userService.deleteUser(id))
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "Get all list user", description = "Get all list user")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/get-all")
    ResponseEntity<List<Users>> getAllListUser(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(userService.getListUser(params), HttpStatus.OK);
    }

    @Operation(summary = "Activate user", description = "Activate user")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}/activate")
    ResponseEntity<String> activateUser(@PathVariable("id") Integer idUser){
        if (userService.activateUser(idUser))
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "Deactivate user", description = "Deactivate user")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}/deactivate")
    ResponseEntity<String> deactivateUser(@PathVariable("id") Integer idUser){
        if (userService.deActivateUser(idUser))
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        return new ResponseEntity<>("FAIL", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Operation(summary = "Give user permission to save article", description = "Give use permission to save article")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}/give-permission-to-save-article")
    ResponseEntity<String> givePermission(@PathVariable("id") Integer idUser){
        return new ResponseEntity<>(userService.givePermissionToSaveArticle(idUser), HttpStatus.OK);
    }

    @Operation(summary = "Denny user to save article", description = "Denny user to save article")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}/denny-permission-to-save-article")
    ResponseEntity<String> dennyPermission(@PathVariable("id") Integer idUser){
        return new ResponseEntity<>(userService.dennyPermissionToSaveArticle(idUser), HttpStatus.OK);
    }


}
