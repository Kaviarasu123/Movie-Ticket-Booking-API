package io.aiven.spring.mysql.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.ui.Model;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Collections;


import java.util.*;

@RestController
@RequestMapping(path = "/demo3")
public class ShowController {
    @Autowired
    private JodhitheatreRepository jodhitheatreRepository;


    @PostMapping("/add")
    public @ResponseBody String addshow(@RequestParam String date, @RequestParam String typeofshow) {
        Jodhitheatreshow jo = new Jodhitheatreshow();
        jo.setDate(date);
        jo.setTypeofshow(typeofshow);

        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Seat seat = new Seat();
            seat.setSeatNumber("Seat " + (i + 1));
            seat.setAvailable(true);
            seat.setShow(jo); // Set the relationship between Seat and Jodhitheatreshow
            seats.add(seat); // Add seat to the list
        }

        jo.setSeats(seats); // Set the list of seats for Jodhitheatreshow
        jodhitheatreRepository.save(jo);

        return "Show added successfully";
    }

    @GetMapping("/show/{id}")
    public @ResponseBody List<Seat> getShowById(@PathVariable Integer id) {
        Optional<Jodhitheatreshow> showOptional = jodhitheatreRepository.findById(id);
        if (showOptional.isPresent()) {
            Jodhitheatreshow show = showOptional.get();
            // Make sure that the seats are eagerly loaded
            List<Seat> list=show.getSeats();// This line forces JPA to load all associated seats
            return list;

        } else{
            return Collections.emptyList();
        }




    }




    @PostMapping("/booking")
    public ResponseEntity<Jodhitheatreshow> bookingSeat(@RequestParam Integer id, @RequestParam int[] seatIndices) {
        Optional<Jodhitheatreshow> showOptional = jodhitheatreRepository.findById(id);
        if (showOptional.isPresent()) {
            Jodhitheatreshow show = showOptional.get();
            List<Seat> seats = show.getSeats();

            for (int seatIndex : seatIndices) {
                if (seatIndex >= 0 && seatIndex < seats.size()) {
                    Seat seat = seats.get(seatIndex);
                    if (seat.getAvailable()) {
                        seat.setAvailable(false); // Book the seat
                    } else {
                        // Seat is already booked, handle accordingly (throw an exception, return an error response, etc.)
                    }
                } else {
                    // Invalid seat index, handle accordingly (throw an exception, return an error response, etc.)
                }
            }

            jodhitheatreRepository.save(show); // Save the updated show with booked seats

            return ResponseEntity.ok(show); // Return the updated show
        } else {
            return ResponseEntity.notFound().build(); // Show not found
        }
    }


}
