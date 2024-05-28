package com.cdweb.bookstore.service.impl;

import com.cdweb.bookstore.converter.UserConverter;
import com.cdweb.bookstore.dto.UserDTO;
import com.cdweb.bookstore.entities.UserEntity;
import com.cdweb.bookstore.repository.UsersRepository;
import com.cdweb.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private UserConverter userConverter;
    @Override
    public UserDTO findByEmailAndIsEnable(String email) {
        //tìm những email đã xác thực (isEnable = true)
        UserEntity userEntity = userRepo.findByEmailIgnoreCaseAndIsEnableAndStatus(email, true, true);
        if (userEntity != null) {
            return userConverter.toDTO(userEntity);
        }
        return null;
    }
}
