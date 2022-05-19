package vttp2022.project.Stock.repositories;

public interface Queries {
    public final static String SQL_CHECK_USER_EXISTS = 
        "select count(*) as login_success from users where username = ? and password = sha1(?)"; 

    public static final String SQL_INSERT_USERS = "INSERT INTO users (username, password) values (?, sha1(?))";

    public static final String SQL_INSERT_TRANSACTION = 
        "INSERT INTO transactions (purchase_date, symbol, company_name, quantity, stock_price, total_price, user_id, stock_status) values (?, ?, ?, ?, ?, ?, ?, ?)";
    
    public static final String SQL_SELECT_USER_TRANSACTIONS = "select * from transactions where user_id = ?";

    // public static final String SQL_CHECK_IF_USER_HAS_MADE_TRANSACTIONS = "select count(*) as company_added from transactions WHERE symbol = ? and user_id = ?";

    public static final String SQL_GET_USER = "select u.user_id from users as u where u.username = ?";

    public static final String SQL_SEARCH_USER = "select * from users where username = ? and password = sha1(?)";

    public static final String SQL_CHECK_IF_USER_HAS_MADE_PURCHASE = "select count(*) as purchase_added from transactions WHERE symbol = ? and stock_price = ? and user_id = ?";

    public static final String SQL_GET_USER_COMPANY_TRANSACTIONS = "select * from transactions where symbol = ? and user_id = ?";
}
