package vttp2022.project.Stock.repositories;

public interface Queries {
    public final static String SQL_CHECK_USER_EXISTS = 
        "select count(*) as login_success from users where username =? and password = sha1(?)"; 
}
