package com.pdstpo.unomas.controllers;

import com.pdstpo.unomas.model.dtos.UserCreateDTO;
import com.pdstpo.unomas.model.dtos.UserResponseDTO;
import com.pdstpo.unomas.model.entities.User;
import com.pdstpo.unomas.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreateDTO userCreateDto) {
        User user = userCreateDto.toEntity();
        User createdUser = userService.create(user);
        UserResponseDTO userResponseDTO = UserResponseDTO.toDTO(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<User> users = userService.findAll();
        List<UserResponseDTO> usersDTO = new ArrayList<>();

        for (User user : users) {
            usersDTO.add(UserResponseDTO.toDTO(user));
        }

        return ResponseEntity.ok(usersDTO);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getByUsername(@PathVariable("username") String username) {
        User user = userService.getByUsername(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(UserResponseDTO.toDTO(user));
    }
}
