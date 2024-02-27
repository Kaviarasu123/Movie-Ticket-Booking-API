package io.aiven.spring.mysql.demo.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class webcontroller {
    @RequestMapping("demo/my-page")
    public String myPage(HttpServletRequest request) {
        return "mypage"; // This should match the name of your Thymeleaf HTML file without the extension
    }
    // Add this method to MainController
    @RequestMapping("demo/Success")
    public String showSuccessPage() {
        return "Success";
    }

    @RequestMapping("demo/display")
    public String showDisplayPage() {
        return "Display";
    }

    @RequestMapping("demo/input")
    public String showinputPage() {
        return "input";
    }

    @RequestMapping("demo/newinput")
    public String shownewinputPage() {
        return "newinput";
    }

    @RequestMapping("demo/updateinput")
    public String showupdateinputtPage() {
        return "updateinput";
    }
    @RequestMapping("demo/updateinfo")
    public String showupdateinfotPage() {
        return "updateinfo";
    }

    @RequestMapping("demo/delete")
    public String showdeleteUserPage() {
        return "deleteuser";
    }

    @RequestMapping("demo/loginpage")
    public String showloginPage() {
        return "loginpage";
    }

    @RequestMapping("demo/createpass")
    public String showcreatepassPage() {
        return "createpass";
    }



}
