package com.example;

public class AuthManager {
    private UserRepo userRepo;
    private HashLibrary hashLibrary;

    public AuthManager(UserRepo userRepo, HashLibrary hashLibrary) {
        this.userRepo = userRepo;
        this.hashLibrary = hashLibrary;
    }

    public User login(String email, String password) throws Exception {
        User user;
        try{
             user = userRepo.findbyEmail(email);
        }
        catch(Exception e){
            throw new Exception("User not found");
        }

            String hashed = hashLibrary.hash(password);
            if( hashed.equals(user.getPassword())){
                return user;
            }
            else {
                throw new Exception("Wrong Password");
            }



        }
    }

