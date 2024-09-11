package com.hiv.demohiv.service;

import com.hiv.demohiv.UserDto;
import com.hiv.demohiv.model.Users;
import com.hiv.demohiv.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users savedUser = userService.save(userDto);
        assertEquals("John Doe", savedUser.getName());
        assertEquals("john.doe@example.com", savedUser.getEmail());
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    void testFindById() {
        Users user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Optional<Users> foundUser = userService.findById(1L);
        assertTrue(foundUser.isPresent());
        assertEquals("John Doe", foundUser.get().getName());
    }

    @Test
    void testFindById_NotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Users> foundUser = userService.findById(1L);
        assertFalse(foundUser.isPresent());
    }

    @Test
    void testFindAll() {
        Users user1 = new Users();
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        Users user2 = new Users();
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");

        List<Users> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<Users> foundUsers = userService.findAll();
        assertEquals(2, foundUsers.size());
        assertEquals("John Doe", foundUsers.get(0).getName());
        assertEquals("Jane Doe", foundUsers.get(1).getName());
    }

    @Test
    void testUpdate() {
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password");

        Users user = new Users();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users updatedUser = userService.update(1L, userDto);
        assertEquals("John Doe", updatedUser.getName());
        assertEquals("john.doe@example.com", updatedUser.getEmail());
        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    void testUpdate_NotFound() {
        UserDto userDto = new UserDto();
        userDto.setName("John Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password");

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.update(1L, userDto));
        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(0)).save(any(Users.class));
    }

    @Test
    void testDelete() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());

        userService.delete(1L);
        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDelete_NotFound() {
        when(userRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.delete(1L));
        verify(userRepository, times(1)).existsById(anyLong());
        verify(userRepository, times(0)).deleteById(anyLong());
    }
}
