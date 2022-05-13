package vttp2022.project.Stock.repositories;

public interface Queries {
    public final static String SQL_CHECK_USER_EXISTS = 
        "select count(*) as login_success from users where username = ? and password = sha1(?)"; 

    public static final String SQL_INSERT_USERS = "INSERT INTO users (username, password) values (?, sha1(?))";

    public static final String SQL_INSERT_TRANSACTION = 
        "INSERT INTO transactions (purchase_date, symbol, company_name, quantity, stock_price, total_price, user_id) values (?, ?, ?, ?, ?, ?, ?)";
    
    public static final String SQL_SELECT_USER_TRANSACTIONS = "select * from transactions where user_id = ?";
}
