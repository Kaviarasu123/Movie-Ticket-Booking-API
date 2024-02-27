package io.aiven.spring.mysql.demo.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String Gender;

    private String Location;

    private Integer age;

    private String password;

    public  Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public  String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public  String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getGender(){
        return Gender;
    }

    public void setGender(String Gender){
        this.Gender=Gender;
    }

    public String getLocation(){
        return Location;
    }

    public void setLocation(String Location){
        this.Location=Location;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age=age;
    }

    public  String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password=password;
    }




    public boolean checkpw(String password, String password1) {
        if(password.equals(password1)){
            return true;
        }
        else{
            return false;
        }

    }
    public boolean verifipw(String password, String password1) {
        if(password.equals(password1)){
            return true;
        }
        else{
            return false;
        }

    }
}
