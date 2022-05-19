package vttp2022.project.Stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.project.Stock.exceptions.UserException;
import vttp2022.project.Stock.repositories.UserRepository;

// import java.util.Collection;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.AuthorityUtils;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import vttp2022.project.Stock.exceptions.UserException;
// import vttp2022.project.Stock.models.User;
// import vttp2022.project.Stock.repositories.UserRepository;

// @Service
// public class UserService implements UserDetailsService{

    @Service
    public class UserService{
    
    @Autowired
    private UserRepository userRepo;

   
    
    // PasswordEncoder passwordEncoder;
    // public boolean authenticate(String username, String password) {
    //     return userRepo.checkUserExists(username, password);
    // }

    // public UserService(UserNewRepository userNewRepository) {
    //     this.userNewRepository = userNewRepository;
    //     this.passwordEncoder = new BCryptPasswordEncoder();
    // }

    // public User save(User user){
    //     user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    //     return this.userNewRepository.save(user);
    // }

    public boolean authenticate(String username, String password) {
        return 1 == userRepo.countUsersByNameAndPassword(username, password);
    }

    // public void createUser(String username, String password) throws UserException{

    //     Integer user = userRepo.countUsersByNameAndPassword(username, password);

    //     if (user == 1)
    //         throw new UserException("%s has already been taken".formatted(username));

    //     if (!userRepo.createUser(username, password))
    //         throw new UserException("Cannot add %s as user. Please contact admin of StockStatus".formatted(username));

    //     createUser(username, password);
    // }

    public void createUser(String username, String password) throws UserException{

        Integer user = userRepo.countUsersByNameAndPassword(username, password);

        if (user == 1)
        {
            throw new UserException("%s has already been taken".formatted(username));
        } else {
            try{
                userRepo.createUser(username, password);
            } catch (Exception ex) {
            throw new UserException("Cannot add %s as user. Please contact admin of StockStatus".formatted(username));
            }
        }
    }

        

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     User inDB = userNewRepository.findByUsername(username);
    //     if (inDB == null) {
    //         throw new UsernameNotFoundException("User not found");
    //     }
    //     return new UserDetails() {

    //         @Override
    //         public Collection<? extends GrantedAuthority> getAuthorities() {
    //             return AuthorityUtils.createAuthorityList("user");
    //         }

    //         @Override
    //         public String getPassword() {
    //             return inDB.getPassword();
    //         }

    //         @Override
    //         public String getUsername() {
    //             return inDB.getUsername();
    //         }

    //         @Override
    //         public boolean isAccountNonExpired() {
    //             return true;
    //         }

    //         @Override
    //         public boolean isAccountNonLocked() {
    //             return true;
    //         }

    //         @Override
    //         public boolean isCredentialsNonExpired() {
    //             return true;
    //         }

    //         @Override
    //         public boolean isEnabled() {
    //             return true;
    //         }
            
    //     };
    // }
    
}
