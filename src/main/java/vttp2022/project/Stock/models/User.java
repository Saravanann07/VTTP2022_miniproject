package vttp2022.project.Stock.models;

// import java.util.Objects;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.SequenceGenerator;
// import javax.persistence.Table;

// @Entity
// @Table
public class User {
    // @Id
    // @SequenceGenerator(
    //     name = "user_sequence",
    //     sequenceName = "user_sequence",
    //     allocationSize = 1
    // )
    // @GeneratedValue(
    //     strategy = GenerationType.SEQUENCE,
    //     generator = "user_sequence"
    // )
    private Integer userId;
    private String username;
    private String password;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
    

    // public User() {
    // }

    // public User(Integer userId, String username, String password) {
    //     this.userId = userId;
    //     this.username = username;
    //     this.password = password;
    // }

    // public Integer getUserId() {
    //     return this.userId;
    // }

    // public void setUserId(Integer userId) {
    //     this.userId = userId;
    // }

    // public String getUsername() {
    //     return this.username;
    // }

    // public void setUsername(String username) {
    //     this.username = username;
    // }

    // public String getPassword() {
    //     return this.password;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    // public User userId(Integer userId) {
    //     setUserId(userId);
    //     return this;
    // }

    // public User username(String username) {
    //     setUsername(username);
    //     return this;
    // }

    // public User password(String password) {
    //     setPassword(password);
    //     return this;
    // }

    // // @Override
    // // public boolean equals(Object o) {
    // //     if (o == this)
    // //         return true;
    // //     if (!(o instanceof User)) {
    // //         return false;
    // //     }
    // //     User user = (User) o;
    // //     return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    // // }

    // // @Override
    // // public int hashCode() {
    // //     return Objects.hash(userId, username, password);
    // // }

    // // @Override
    // // public String toString() {
    // //     return "{" +
    // //         " userId='" + getUserId() + "'" +
    // //         ", username='" + getUsername() + "'" +
    // //         ", password='" + getPassword() + "'" +
    // //         "}";
    // // }


    // public static Integer getUserId() {
    //     return userId;
    // }
    // public void setUserId(Integer userId) {
    //     User.userId = userId;
    // }
    
    // public String getUsername() {
    //     return username;
    // }
    // public void setUsername(String username) {
    //     this.username = username;
    // }
    // public String getPassword() {
    //     return password;
    // }
    // public void setPassword(String password) {
    //     this.password = password;
    // }
// }
