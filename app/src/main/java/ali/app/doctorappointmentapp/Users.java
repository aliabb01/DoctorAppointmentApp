package ali.app.doctorappointmentapp;

public class Users {
    private String name;
    private String email;
    private String password;
    private Integer isadmin;

    public Users(String name, String email, String password, Integer isadmin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isadmin = isadmin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }
}
