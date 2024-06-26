package com.nmn.dto;

import com.nmn.model.enumType.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Integer id;

    private String username;
    private  String password;


    private String role;

    private Boolean isActivate;
    private Boolean canSaveArticle;

}
