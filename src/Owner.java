public class Owner extends User {
 
    String username = "admin";
    String password = "12345";

    @Override
    public String getUsername() {

        return this.username;
    } // end getUsername 

    @Override
    public String getPassword() {

        return this.password;
    } // end getPassword

} // end Owner class