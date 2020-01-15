package com.xych.bookkeeping.app.common.support;

import org.springframework.stereotype.Component;

import com.xych.bookkeeping.dao.dto.UserDTO;

@Component
public class UserSupport {
    private static UserDTO userDTO = new UserDTO();
    
    {
        userDTO.setId(1L);
        userDTO.setUserCode("xych");
    }

    public UserDTO getUser() {
        return userDTO;
    }
}
