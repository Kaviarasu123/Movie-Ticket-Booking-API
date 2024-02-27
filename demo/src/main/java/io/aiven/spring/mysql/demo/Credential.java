package io.aiven.spring.mysql.demo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    public int getId(){
         return id;
    }

    public void setId(){
        this.id=id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPasword(String password) {
        this.password = password;
    }

    public boolean checkpw(String password, String password1) {
        if(password.equals(password1)){
            return true;
        }
        else{
            return false;
        }

    }


}
