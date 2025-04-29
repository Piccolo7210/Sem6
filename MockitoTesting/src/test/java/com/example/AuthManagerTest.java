package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthManagerTest {

    private UserRepo userRepo;
    private HashLibrary hashLibrary;
    private AuthManager authManager;
    public String actualEmail ;
    public String actualPassword ;
    public String actualHashedPassword ;
    public String fakePassword ;
    public String unknownEmail;

    private User mockUser;

    @BeforeEach
    void setUp() throws Exception {
        actualEmail = "user@example.com";
        actualPassword = "password123";
        actualHashedPassword = "hashed123";
        fakePassword = "wrongPassword";
        unknownEmail= "unknown@example.com";
        userRepo = mock(UserRepo.class);
        hashLibrary = mock(HashLibrary.class);
        authManager = new AuthManager(userRepo, hashLibrary);
        mockUser = new User();
        mockUser.setEmail(actualEmail);
        mockUser.setPassword(actualHashedPassword);
        when(userRepo.findbyEmail(actualEmail)).thenReturn(mockUser);
        when(hashLibrary.hash(actualPassword)).thenReturn(actualHashedPassword);
        when(hashLibrary.hash(fakePassword)).thenReturn("wrongHashed");
        doThrow(new Exception("User not found")).when(userRepo).findbyEmail(unknownEmail);
    }

    @Test
    void testLoginSuccess() throws Exception {
        User result = authManager.login(actualEmail, actualPassword);

        assertNotNull(result);
        assertInstanceOf(User.class, result);
        assertEquals(actualEmail, result.getEmail());
    }

    @Test
    void testAuthenticateFail_WrongPassword() throws Exception {
        Exception ex = assertThrows(Exception.class, () ->
                authManager.login(actualEmail, fakePassword)
        );

        assertEquals("Wrong Password", ex.getMessage());
    }

    @Test
    void testAuthenticateFail_UserNotFound() throws Exception {
        Exception ex = assertThrows(Exception.class, () ->
                authManager.login(unknownEmail, "anyPassword")
        );

        assertEquals("User not found", ex.getMessage());
    }
}
