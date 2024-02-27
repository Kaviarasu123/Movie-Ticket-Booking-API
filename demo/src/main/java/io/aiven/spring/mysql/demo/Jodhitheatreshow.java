package io.aiven.spring.mysql.demo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Jodhitheatreshow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;
    private String typeofshow;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @Lob
    @JsonIgnore // Add this annotation to break the loop
    private List<Seat> seats;
    // Getters and setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeofshow() {
        return typeofshow;
    }

    public void setTypeofshow(String typeofshow) {
        this.typeofshow = typeofshow;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
