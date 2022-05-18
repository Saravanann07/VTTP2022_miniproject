package vttp2022.project.Stock.repositories;

import static vttp2022.project.Stock.repositories.Queries.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.project.Stock.models.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate template;

    // public boolean checkUserExists(String username, String password) {
    //     final SqlRowSet result = template.queryForRowSet(SQL_CHECK_USER_EXISTS, username, password);
    //     if (!result.next()) 
    //         return false;
    //     System.out.println(">>>>>>>>>>>" + result.getInt("login_success"));
    //     return true;
        
    // }

    public int countUsersByNameAndPassword(String username, String password) {
        SqlRowSet rs = template.queryForRowSet(SQL_CHECK_USER_EXISTS, username, password);
        if (!rs.next())
            return 0;
        return rs.getInt("login_success");
    }

    // @Query("select u.user_id from users as u where u.username = ?1")
    // public int getUser 

    // public int getUser(String username){
    // SqlRowSet rs = template.queryForRowSet(SQL_GET_USER, username);

    // return rs.getInt("user_id");
    // }
    // public Optional<Integer> userAlreadyExists(String username, String password) {
    //     final SqlRowSet rs = template.queryForRowSet(Queries.SQL_CHECK_USER_EXISTS, username, password);
    //     int count = 0;
    //     if (!rs.next())
    //         return Optional.empty();
        
    //         count ++;
    //         return Optional.of(count);
    // }

    public boolean createUser(String username, String password) {
        int count = template.update(Queries.SQL_INSERT_USERS, username, password);

        return 1 == count;
    }

    public User getUser(String username, String password) {
        SqlRowSet rs = template.queryForRowSet(SQL_SEARCH_USER, username, password);
        if (!rs.next())
            return null;
       User user = new User();
       user.setUserId(rs.getInt("user_id"));
       user.setUsername(rs.getString("username"));
       user.setPassword(rs.getString("password"));
        return user;
    }


    
}


    
