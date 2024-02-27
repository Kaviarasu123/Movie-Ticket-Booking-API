package io.aiven.spring.mysql.demo.Controller;

import io.aiven.spring.mysql.demo.Model.User;
import io.aiven.spring.mysql.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String processAddUserForm(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam Integer age,
                                     @RequestParam String Location,
                                     @RequestParam String Gender,
                                     @RequestParam String password,
                                     Model model) {
        // Create a new user and save it to the database
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setAge(age);
        newUser.setGender(Gender);
        newUser.setLocation(Location);
        newUser.setPassword(password);

        userRepository.save(newUser);

        // Pass data to the success page
        model.addAttribute("name", name);

        // Redirect to the success page
        return "Success";
    }


    @GetMapping(path = "/all")
    public String getAllUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "Display"; // This corresponds to the name of your HTML file (users.html)
    }

    @GetMapping(path = "all/userid")
    public String getUserById(@RequestParam(required = false) Integer userId, Model model) {
        if (userId != null) {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                model.addAttribute("user", user);
            }
        }
        return "userDetails"; // This corresponds to the name of your HTML file (userDetails.html)
    }

    // New method for updating an existing user using PUT
    @PostMapping("/add/useridd")
    public String updateUser(@RequestParam Integer userId,
                                           @RequestParam String name,
                                           @RequestParam String email,
                                           @RequestParam Integer age,
                                           @RequestParam String Location,
                                           @RequestParam String Gender,@RequestParam String password,Model model) {


        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setName(name);
            existingUser.setEmail(email);
            existingUser.setAge(age);
            existingUser.setGender(Gender);
            existingUser.setLocation(Location);
            existingUser.setPassword(password);

            userRepository.save(existingUser);
            model.addAttribute("name", name);

            return "updateinfo";
        } else {
            return "update info";
        }
    }

    @GetMapping (path = "/delete/useridd")
    public String deleteUserById(@RequestParam Integer userId,Model model) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            userRepository.deleteById(userId);

            model.addAttribute("ID",userId);

            return "deletionpage";

        }
        else{
            return "deletionpage";
        }
    }
    @GetMapping ("/deleteall")
    public @ResponseBody String deleteAllUser(){
         userRepository.deleteAll();
         return "record all delete";
    }

    @GetMapping ("/login.com")
    public String Login(@RequestParam String email,@RequestParam String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            User user = userOptional.get();

                // Check password match (use a secure password hashing library)
                if (user.checkpw(password, user.getPassword())) {
                    return "loginpage2";
                } else {
                    return "loginpage2";
                }

        }
        else{
            return "Email is not found";
        }

    }
    //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
    @PostMapping(path="passreset")
    public String passwordreset(@RequestParam String email,@RequestParam String password,@RequestParam String newpassword,Model model){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.verifipw(newpassword, user.getPassword())) {
                model.addAttribute("msg", "password already taken create strong password");
                return "passcreated";
            }
            else{


            // Check password match (use a secure password hashing library)
            if (user.checkpw(password, user.getPassword())) {
                User existingUserch = userOptional.get();
                existingUserch.setPassword(newpassword);
                userRepository.save(existingUserch);


                model.addAttribute("msg", "password reset successful");
                return "passcreated";
            } else {

                model.addAttribute("msg", "Incorrect password");
                return "passcreated";
            }
        }

        }
        else{

            model.addAttribute("msg","Email is not found");
            return "passcreated";
        }

    }


}






