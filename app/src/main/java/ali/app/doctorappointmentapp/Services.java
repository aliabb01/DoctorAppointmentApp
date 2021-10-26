package ali.app.doctorappointmentapp;

public class Services {
    private Integer id;
    private String name;
    private String description;


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
