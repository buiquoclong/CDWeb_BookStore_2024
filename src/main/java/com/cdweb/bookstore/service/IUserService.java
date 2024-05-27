package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.UserDTO;

import java.util.List;

public interface IUserService {
    public UserDTO findByEmailAndIsEnable(String email);

    public UserDTO sendMail(UserDTO user);

    public String getConfirmCode(int id);

    public UserDTO confirmEmail(int id);


    public List<UserDTO> findAllUser();

    public UserDTO findByUserId(int id);

    public void deleteByUserId(int id);

    public void save(UserDTO user);
}