package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.UserDTO;

public interface IUserService {
    public UserDTO findByEmailAndIsEnable(String email);

}
