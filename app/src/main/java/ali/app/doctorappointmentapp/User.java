package ali.app.doctorappointmentapp;

import java.io.Serializable;

public class User implements Serializable {

    private  String Name;
    private String Email;
    private String password;
    private  String Role;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public User(String name, String email) {
        this.Name = name;
        this.Email = email;
    }

    public User() {

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
