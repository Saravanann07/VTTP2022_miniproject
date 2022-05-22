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

    public int countUsersByNameAndPassword(String username, String password) {
        SqlRowSet rs = template.queryForRowSet(SQL_CHECK_USER_EXISTS, username, password);
        if (!rs.next())
            return 0;
        return rs.getInt("login_success");
    }

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

    // public boolean deleteUser (String username) {
    //     int count = template.update(SQL_DELETE_USER, username);
    //     return 1 == count;
    // }
    
}


    
