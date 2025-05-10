package com.example.users.service;

import com.example.users.DTO.user.*;
import com.example.users.models.users;
import com.example.users.errors.exceptions.userExceptions.UserAlreadyExist;
import com.example.users.repository.userRepository;
import com.example.users.service.tokenService;
import com.example.users.DTO.token.Payload;
import com.example.users.DTO.token.TokensResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestUserService {

    @Mock
    private userRepository UserRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private tokenService TokenService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private userService UserService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistration_NewUser_Success() {

        registrationDTO dto = new registrationDTO();
        dto.setName("777");
        dto.setNumberPhone("123456");
        dto.setPassword("password");

        users mappedUser = new users();
        mappedUser.setId(1);
        mappedUser.setRole("Покупатель");

        TokensResponse mockTokens = new TokensResponse("access123", "refresh123");

        when(UserRepository.findByNumberPhone(dto.getNumberPhone())).thenReturn(null);
        when(modelMapper.map(dto, users.class)).thenReturn(mappedUser);
        when(TokenService.generateTokens(any(Payload.class))).thenReturn(mockTokens);

        Map<String, Object> result = UserService.registration(dto);

        assertNotNull(result);
        assertEquals(mappedUser, result.get("user"));
        assertEquals(mockTokens, result.get("tokens"));
        verify(UserRepository, times(1)).save(mappedUser);
    }

    @Test
    public void testRegistration_ExistingUser_ThrowsException() {

        registrationDTO dto = new registrationDTO();
        dto.setNumberPhone("123456");

        when(UserRepository.findByNumberPhone(dto.getNumberPhone())).thenReturn(new users());

        assertThrows(UserAlreadyExist.class, () -> UserService.registration(dto));
    }

    @Test
    public void testFindByPhoneNumber_Success() {

        String phoneNumber = "1234567890";
        users mockedUser = new users();
        mockedUser.setId(1);
        mockedUser.setNumberPhone(phoneNumber);
        mockedUser.setRole("Покупатель");

        Mockito.when(UserRepository.findByNumberPhone(phoneNumber)).thenReturn(mockedUser);

        users foundUser = UserService.findByPhoneNumber(phoneNumber);

        assertNotNull(foundUser);
        assertEquals(mockedUser.getId(), foundUser.getId());
        assertEquals(mockedUser.getNumberPhone(), foundUser.getNumberPhone());
        assertEquals(mockedUser.getRole(), foundUser.getRole());

        verify(UserRepository, times(1)).findByNumberPhone(phoneNumber);
    }

    @Test
    public void testFindByPassword_Success() {
        
        String name = "JohnDoe";
        String password = "password123";

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        users mockUser = new users();
        mockUser.setName(name);
        mockUser.setPassword(encodedPassword); 

        List<users> usersList = new ArrayList<>();
        usersList.add(mockUser);

        when(UserRepository.findAll()).thenReturn(usersList);

        System.out.println(UserService.checkPassword(password, mockUser.getPassword()) + " -->UserService.checkPassword(password, mockUser.getPassword())");

        when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(true);

        CompletableFuture<users> future = CompletableFuture.supplyAsync(() -> UserService.findByPassword(name, password));

        users result = future.join();

        assertNotNull(result);
        assertEquals(mockUser.getName(), result.getName());

        verify(UserRepository, times(1)).findAll();
        verify(passwordEncoder, times(1)).matches(password, mockUser.getPassword());
    }

    // @Test
    // public void testFindByPassword_IncorrectPassword() {
    //     // Подготовка данных
    //     String name = "JohnDoe";
    //     String password = "wrongPassword";
    //     users mockUser = new users();
    //     mockUser.setName(name);
    //     mockUser.setPassword("$2a$10$W6yPvIZmeo7frMubHXlT/eh6Ej6PzOy6mE7KJ35ZY9wofU69wKyDW"); // bcrypt-строка для "password123"

    //     List<users> usersList = new ArrayList<>();
    //     usersList.add(mockUser);

    //     // Мокируем поведение репозитория
    //     when(UserRepository.findAll()).thenReturn(usersList);
    //     when(passwordEncoder.matches(password, mockUser.getPassword())).thenReturn(false);

    //     // Вызываем метод
    //     users result = UserService.findByPassword(name, password);

    //     // Проверяем, что метод вернул null, так как пароль неверный
    //     assertNull(result);

    //     // Проверяем, что методы были вызваны
    //     verify(UserRepository, times(1)).findAll();
    //     verify(passwordEncoder, times(1)).matches(password, mockUser.getPassword());
    // }
    
    @Test
    public void testFindByPassword_UserNotFound() {
        String name = "UnknownUser";
        String password = "password123";

        List<users> usersList = new ArrayList<>();

        when(UserRepository.findAll()).thenReturn(usersList);

        users result = UserService.findByPassword(name, password);

        assertNull(result);

        verify(UserRepository, times(1)).findAll();
    } 
}
