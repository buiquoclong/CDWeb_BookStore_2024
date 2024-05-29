package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.UserDTO;

import java.util.List;

public interface IUserService {
    public UserDTO findByEmailAndIsEnable(String email);

    public UserDTO sendMail(UserDTO user);

    public String getConfirmCode(int id);

    public UserDTO confirmEmail(int id);
    public UserDTO sendMailForgotPassword(String userEmail);
    //change information
    public void changeInformation(UserDTO user);
    //change password
    public boolean checkPass(String email, String password);

    public void changePassword(String password, String email);

    public List<UserDTO> findAllUser();

    public UserDTO findByUserId(int id);

    public void deleteByUserId(int id);

    public void save(UserDTO user);
    public void processOAuthPostLogin(String email, String username);
}
