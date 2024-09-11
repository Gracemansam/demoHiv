package com.hiv.demohiv.controller;

import com.hiv.demohiv.UserDto;
import com.hiv.demohiv.model.Users;
import com.hiv.demohiv.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SampleControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private SampleController sampleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHello() {
        String response = sampleController.hello();
        assertEquals("Hello from plugin", response);
    }

    @Test
    void testSave() {
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password");

        Users user = new Users();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        when(userService.save(any(UserDto.class))).thenReturn(user);

        Users response = sampleController.save(userDto);
        assertEquals("John Doe", response.getName());
        assertEquals("john.doe@example.com", response.getEmail());
    }

    @Test
    void testGetUserById() {
        Users user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        when(userService.findById(anyLong())).thenReturn(Optional.of(user));

        ResponseEntity<Users> response = sampleController.getUserById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userService.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Users> response = sampleController.getUserById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllUsers() {
        Users user1 = new Users();
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        Users user2 = new Users();
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");

        List<Users> users = Arrays.asList(user1, user2);

        when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<Users>> response = sampleController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password");

        Users user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        when(userService.update(anyLong(), any(UserDto.class))).thenReturn(user);

        ResponseEntity<Users> response = sampleController.updateUser(1L, userDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testUpdateUser_NotFound() {
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password");

        when(userService.update(anyLong(), any(UserDto.class))).thenThrow(new EntityNotFoundException());

        ResponseEntity<Users> response = sampleController.updateUser(1L, userDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).delete(anyLong());

        ResponseEntity<Void> response = sampleController.deleteUser(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteUser_NotFound() {
        doThrow(new EntityNotFoundException()).when(userService).delete(anyLong());

        ResponseEntity<Void> response = sampleController.deleteUser(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
