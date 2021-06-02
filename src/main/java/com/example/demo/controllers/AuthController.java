package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Controller
public class AuthController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/auth")
    public String loginPage(){
        return "auth";
    }


    @PostMapping(value = "/registration",consumes = "application/json")

    public void registration(
//                                @RequestParam(name = "firstname") String firstname,
//                                @RequestParam(name = "lastname") String lastname,
//                                @RequestParam(name = "username") String username,
//                                @RequestParam(name = "email") String email,
//                                @RequestParam(name = "id") String id
            @RequestBody RegistrationRequest rr
                            ) throws ExecutionException, InterruptedException {
//        System.out.println(username+" "+lastname+" "+firstname+" "+id+" "+email);

        User user = new User(rr.getId(),rr.getUsername(),rr.getFirstname(),rr.getLastname(),rr.getEmail(),Role.USER);

        user.setRole(Role.USER);
        userRepo.addUser(user);
        System.out.println("user with email:"+user.getEmail()+" succesfully registered!");
    }

//    @GetMapping("/firebaseConfig")
//    @ResponseBody
//    public JSONObject getConfig(){
//        JSONObject jsonObject = null;
//        JSONParser parser = new JSONParser();
//        try {
//            Object obj = parser.parse(new FileReader("C:\\Users\\Пользователь\\IdeaProjects\\speech2text_translator\\src\\main\\resources\\config.json"));
//            jsonObject = (JSONObject) obj;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }

    @GetMapping("/homepage")
    public String home(){
        return "main_page";
    }

    
    @PostMapping ("/login")
    @ResponseBody
    public User login(@RequestParam("id") String id,
                        Model model) throws ExecutionException, InterruptedException {
        User user = userRepo.getUserById(id);
        model.addAttribute("user",user);
        return user;
    }




}
