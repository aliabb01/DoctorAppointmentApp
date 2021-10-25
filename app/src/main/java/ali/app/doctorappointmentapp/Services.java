package ali.app.doctorappointmentapp;

public class Services {
    private String name;
    private String description;
    private int user_id ;

    public Services(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Services() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
