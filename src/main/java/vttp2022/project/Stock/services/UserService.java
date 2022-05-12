package vttp2022.project.Stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.project.Stock.exceptions.UserException;
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

    public void createUser(String username, String password) throws UserException{

        Integer user = userRepo.countUsersByNameAndPassword(username, password);

        if (user == 1)
            throw new UserException("%s has already been taken".formatted(username));

        if (!userRepo.createUser(username, password))
            throw new UserException("Cannot add %s as user. Please contact admin of StockStatus".formatted(username));

        createUser(username, password);
    }
    
}
