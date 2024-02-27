package io.aiven.spring.mysql.demo;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String theatername;

    private String location;

    private String theatretype;

    private String address;


    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    @JsonIgnore // Add this annotation to break the loop
    private List<Jodhitheatreshow> shows;

    public List<Jodhitheatreshow> getShows() {
        return shows;
    }

    public void setShows(List<Jodhitheatreshow> shows) {
        this.shows = shows;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheatername() {
        return theatername;
    }

    public void setTheatername(String theatername) {
        this.theatername = theatername;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTheatretype() {
        return theatretype;
    }

    public void setTheatretype(String theatretype) {
        this.theatretype = theatretype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
