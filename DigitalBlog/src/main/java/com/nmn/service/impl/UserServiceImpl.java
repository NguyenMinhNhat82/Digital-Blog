package com.nmn.service.impl;

import com.nmn.dto.UserDTO;
import com.nmn.dto.mapper.UserMapper;
import com.nmn.model.Users;
import com.nmn.repository.UserRepository;
import com.nmn.repository.UserRepositoryCus;
import com.nmn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepositoryCus userRepositoryCus;

    @Override
    public Users readProfile(Integer id) {
        return userRepository.findUsersById(id);
    }

    @Override
    public Boolean changePassword(Integer id, String password) {
        try {
            Users user = userRepository.findUsersById(id);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            userRepository.save(user);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    public Users addOrUpdateProfile(UserDTO userDTO) {
        return userRepository.save(userMapper.toEntity(userDTO));
    }
    @Override
    public Boolean deleteUser(Integer id) {
        try {
            userRepository.delete(userRepository.findUsersById(id));
            return  true;
        }
        catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<Users> getListUser(Map<String, String> params) {
        return userRepositoryCus.getListUser(params);
    }

    @Override
    public Boolean activateUser(Integer id) {
        try {
            Users user = userRepository.findUsersById(id);
            user.setIsActivate(true);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    public Boolean deActivateUser(Integer id) {
        try {
            Users user = userRepository.findUsersById(id);
            user.setIsActivate(false);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    public Users findUserByUserName(String userName) {
        return userRepository.findUsersByUsername(userName);
    }

    @Override
    public Users getUserbyId(Integer id) {
        return userRepository.findUsersById(id);
    }

    @Override
    public String givePermissionToSaveArticle(Integer id) {
        try{
            Users user = userRepository.findUsersById(id);
            user.setCanSaveArticle(true);
            userRepository.save(user);
            return  "SUCCESS";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }

    @Override
    public String dennyPermissionToSaveArticle(Integer id) {
        try{
            Users user = userRepository.findUsersById(id);
            user.setCanSaveArticle(false);
            userRepository.save(user);
            return  "SUCCESS";
        }catch (Exception ex){
            return ex.getMessage();
        }
    }


}
