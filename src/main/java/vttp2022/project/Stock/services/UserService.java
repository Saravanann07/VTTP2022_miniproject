package vttp2022.project.Stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.project.Stock.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // public boolean authenticate(String username, String password) {
    //     return userRepo.checkUserExists(username, password);
    // }

    public boolean authenticate(String username, String password) {
        return 1 == userRepo.countUsersByNameAndPassword(username, password);
    }
    
}
