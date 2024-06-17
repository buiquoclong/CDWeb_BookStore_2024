package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.AuthorDTO;

import java.util.List;

public interface IAuthorService {
    public List<AuthorDTO> findAll();

    public void save(AuthorDTO author);

    public void update(AuthorDTO author, int id);

    public AuthorDTO findById(int id);

    public void delete(int id);
}
