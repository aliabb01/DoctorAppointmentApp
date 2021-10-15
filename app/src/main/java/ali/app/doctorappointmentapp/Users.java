package ali.app.doctorappointmentapp;

import java.io.Serializable;

public class Users implements Serializable {

    private  String Name;
    private String Email;
    private String password;
    private  String Role;

    public Users(String name, String email) {
        this.Name = name;
        this.Email = email;
    }

    public Users() {

    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return Role;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
