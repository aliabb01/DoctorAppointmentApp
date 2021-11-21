package ali.app.doctorappointmentapp;

import java.io.Serializable;

public class Services implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Integer user_id;

    public Services(Integer id, String name, String description, Integer user_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Services() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Services(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Services(String name, String description) {
        this.name = name;
        this.description = description;
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
