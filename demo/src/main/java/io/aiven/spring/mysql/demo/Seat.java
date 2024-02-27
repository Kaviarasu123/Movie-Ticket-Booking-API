package io.aiven.spring.mysql.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String seatNumber;
    private Boolean available;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Jodhitheatreshow show;

    public Jodhitheatreshow getShow() {
        return show;
    }

    public void setShow(Jodhitheatreshow show) {
        this.show = show;
    }
// Getters and setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }




}
