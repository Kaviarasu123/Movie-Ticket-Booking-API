package io.aiven.spring.mysql.demo.Controller;
import io.aiven.spring.mysql.demo.Model.*;
import io.aiven.spring.mysql.demo.Repository.CredentialRepository;
import io.aiven.spring.mysql.demo.Repository.JodhitheatreRepository;
import io.aiven.spring.mysql.demo.Repository.MoviesRepository;
import io.aiven.spring.mysql.demo.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/demo2")
public class MainController2 {


    private CredentialRepository credentialRepository;
    private TheatreRepository theatreRepository;
    private MoviesRepository moviesRepository;
    private JodhitheatreRepository jodhitheatreRepository;


    @Autowired
    public MainController2(CredentialRepository credentialRepository, TheatreRepository theatreRepository, MoviesRepository moviesRepository,JodhitheatreRepository jodhitheatreRepository) {
        this.credentialRepository = credentialRepository;
        this.theatreRepository = theatreRepository;
        this.moviesRepository = moviesRepository;
        this.jodhitheatreRepository=jodhitheatreRepository;


    }

    @PostMapping("/add")
    public @ResponseBody String Addaccount(@RequestParam String email, @RequestParam String password) {

        Credential newacc = new Credential();
        newacc.setEmail(email);
        newacc.setPasword(password);

        credentialRepository.save(newacc);

        return "account add successfully";

    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Credential> showallaccount() {
        return credentialRepository.findAll();
    }

    @GetMapping(path = "all/{email}")
    public @ResponseBody ResponseEntity<?> getUserById(@PathVariable String email) {
        if (email != null) {
            Optional<Credential> op = credentialRepository.findByEmail(email);
            if (op.isPresent()) {
                Credential credential = op.get();
                return new ResponseEntity<>(credential, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
        // This corresponds to the name of your HTML file (userDetails.html)
    }

    @GetMapping("/deleteall")
    public @ResponseBody String deleteAllCredential() {
        credentialRepository.deleteAll();
        return "record all delete";
    }

    @GetMapping("/delete/{userid}")
    public @ResponseBody String deleteaccbyid(@PathVariable Integer userid) {
        Optional<Credential> userOptional = credentialRepository.findById(userid);
        if (userOptional.isPresent()) {
            Credential existingUser = userOptional.get();
            credentialRepository.deleteById(userid);
            return "id deleted";
        } else {
            return "id not present";
        }
    }

    @PostMapping("/login.com")
    public @ResponseBody String Login(@RequestParam String email, @RequestParam String password) {
        Optional<Credential> userOptional = credentialRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Credential cred = userOptional.get();

            // Check password match (use a secure password hashing library)
            if (cred.checkpw(password, cred.getPassword())) {
                return "login successfull";
            } else {
                return "incorrected password";
            }

        } else {
            return "Email is not found";
        }

    }

    @PostMapping("/addtheatre")
    public @ResponseBody String showtheatre(@RequestParam String theatrename,
                                            @RequestParam String location,
                                            @RequestParam String theatretype,
                                            @RequestParam String address,
                                            @RequestParam String[] dates,
                                            @RequestParam String[] typeofshowss) {
        Theatre theatre = new Theatre();
        theatre.setTheatername(theatrename);
        theatre.setLocation(location);
        theatre.setTheatretype(theatretype);
        theatre.setAddress(address);

        List<Jodhitheatreshow> shows = new ArrayList<>();

        for (int i = 0; i < Math.min(dates.length, typeofshowss.length); i++) {
            Jodhitheatreshow jo = new Jodhitheatreshow();
            jo.setDate(dates[i]);
            jo.setTypeofshow(typeofshowss[i]);

            List<Seat> seats = new ArrayList<>();
            for (int j = 0; j < 50; j++) {
                Seat seat = new Seat();
                seat.setSeatNumber("Seat " + (j + 1));
                seat.setAvailable(true);
                seat.setShow(jo); // Set the relationship between Seat and Jodhitheatreshow
                seats.add(seat); // Add seat to the list
            }
            jo.setTheatre(theatre);
            jo.setSeats(seats); // Set the list of seats for Jodhitheatreshow
            shows.add(jo); // Add the show to the list of shows
        }

        theatre.setShows(shows); // Set the list of shows for Theatre
        theatreRepository.save(theatre);

        return "Theatre added successfully";
    }


    @GetMapping("/showtheatre")
    public @ResponseBody Iterable<Theatre> showalltheatre() {
        return theatreRepository.findAll();
    }

    @GetMapping("/delete/theatre/{userid}")
    public @ResponseBody String deletetheatrebyid(@PathVariable Integer userid) {
        Optional<Theatre> userOptional = theatreRepository.findById(userid);
        if (userOptional.isPresent()) {
            Theatre existingUser = userOptional.get();
            theatreRepository.deleteById(userid);
            return "id deleted";
        } else {
            return "id not present";
        }
    }

    //************************************************************************************************************
    @PostMapping("/addtheatre/{userId}")
    public String updateUser(@PathVariable Integer userId, @RequestParam String theatrename, @RequestParam String location, @RequestParam String theatretype, @RequestParam String address) {


        Optional<Theatre> userOptional = theatreRepository.findById(userId);

        if (userOptional.isPresent()) {
            Theatre existingUser = userOptional.get();
            existingUser.setTheatername(theatrename);
            existingUser.setLocation(location);
            existingUser.setTheatretype(theatretype);
            existingUser.setAddress(address);


            theatreRepository.save(existingUser);


            return "theatreupdate";
        } else {
            return "give id properly";
        }
    }

    @PostMapping("/addmovie")
    public @ResponseBody String addingmovies(@RequestParam String movie, @RequestParam String dimension, @RequestParam String language, @RequestParam String duration, @RequestParam String genre, @RequestParam String relesedate, @RequestParam String outline) {
        Movies movies=new Movies();
        movies.setMovie(movie);
        movies.setDimension(dimension);
        movies.setLanguage(language);
        movies.setDuration(duration);
        movies.setGenre(genre);
        movies.setReleasedate(relesedate);
        movies.setOutline(outline);
        moviesRepository.save(movies);
        return "movie added successfully";
    }

    @GetMapping("/showmovie")
    public @ResponseBody Iterable<Movies> showallMovies() {
        return moviesRepository.findAll();
    }
    @GetMapping("/delete/moviebyid/{userid}")
    public @ResponseBody String DeleteMovie(@PathVariable Integer userid){
        Optional<Movies> moviesOp=moviesRepository.findById(userid);
        if(moviesOp.isPresent()){
            Movies mv=moviesOp.get();
            moviesRepository.deleteById(userid);
            return "Movie deleted successfully";
        }
        else{
            return "movie is not present";
        }

    }
//*************************************************************************
@PostMapping("/addmovie/{userid}")
public String updateMovie(@PathVariable Integer userid, @RequestParam String movie, @RequestParam String dimension, @RequestParam String language, @RequestParam String duration,@RequestParam String genre,@RequestParam String relesedate,@RequestParam String outline) {


    Optional<Movies> movieOp = moviesRepository.findById(userid);

    if (movieOp.isPresent()) {
        Movies existingmovie = movieOp.get();
        existingmovie.setMovie(movie);
        existingmovie.setDimension(dimension);
        existingmovie.setLanguage(language);
        existingmovie.setDuration(duration);
        existingmovie.setGenre(genre);
        existingmovie.setReleasedate(relesedate);
        existingmovie.setOutline(outline);


        moviesRepository.save(existingmovie);


        return "movie is update";
    } else {
        return "give id properly";
    }
}


}
