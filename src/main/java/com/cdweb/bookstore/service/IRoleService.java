package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.RoleDTO;

public interface IRoleService {
    public RoleDTO findRolebyName(String name);
}
