import java.io.Serializable;

public abstract class User implements Serializable{
    
    String username = "Username";
    String password = "Password";

    public void setUsername(String user) {

        this.username = user;
    } // end setUsername

    public String getUsername() {

        return this.username;
    } // end getUsername 

    public void setPassword(String pass) {

        this.password = pass;
    } // end setPassword

    public String getPassword() {

        return this.password;
    } // end getPassword 
    
} // end User class