package ali.app.doctorappointmentapp;

public class Users {
    private  String Name;
    private String Email;

    public Users(String name, String email) {
        this.Name = name;
        this.Email = email;
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
