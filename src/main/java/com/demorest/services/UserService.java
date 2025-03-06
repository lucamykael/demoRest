package com.demorest.services;

import com.demorest.dtos.UserDTO;
import com.demorest.entities.User;
import com.demorest.exceptions.NotFoundException;
import com.demorest.mappers.UserMapper;
import com.demorest.repositories.UserRepository;
import com.demorest.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository repository;
    UserMapper mapper;
    UserSpecification specification;

    public Collection<User> getUsers(User user) {

        if(user == null){
            return repository.findAll();
        }

        Specification<User> spec = specification.buildSpecification(user);

        return repository.findAll(spec);
    }

    public User create(UserDTO dto) throws Exception{

        var user = mapper.buildEntity(dto);

        return repository.save(user);
    }

    public User update(User dto){

        User user = (User) repository.getUserById(dto.getId());

        if(user == null){
            throw new NotFoundException("User not found");
        }

        user.setName(dto.getName() == null ? user.getName() : dto.getName());
        user.setEmail(dto.getEmail() == null ? user.getEmail() : dto.getEmail());

        return repository.save(user);
    }

    public User delete(UUID id){

        User user = (User) repository.getUserById(id);

        if(user == null){
            throw new NotFoundException("User not found");
        }

        repository.delete(user);

        return user;
    }
}