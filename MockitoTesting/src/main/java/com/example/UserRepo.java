package com.example;

public interface UserRepo {
    public User findbyEmail(String email) throws Exception;
}

