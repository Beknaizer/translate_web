package com.example.demo.controllers;

import com.example.demo.IServices.*;
import com.example.demo.models.TranslateHistory;
import com.example.demo.models.User;
import com.example.demo.repositories.TranslateHistoryRepo;
import com.example.demo.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Controller
public class TranslateController {

    @Autowired
    private ITranslateTextService textTranslator;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TranslateHistoryRepo translateHistoryRepo;

    @GetMapping("/translate")
    @ResponseBody
    public String translate(
                            @RequestParam(name="userEmail") String userEmail,
                            @RequestParam(name="textToTranslate") String textToTranslate,
                            @RequestParam(name="fromLanguage") String fromLanguage,
                            @RequestParam(name="toLanguage") String toLanguage) throws ExecutionException, InterruptedException {

        String translatedText = textTranslator.translateText(textToTranslate, toLanguage, fromLanguage);
        if (userEmail != null) {
            TranslateHistory translateHistory = new TranslateHistory(userEmail, textToTranslate, translatedText, fromLanguage, toLanguage);
            translateHistoryRepo.addTranslateHistory(translateHistory);
        }
        return translatedText;
    }

    @GetMapping("/translateForAndroid")
    @ResponseBody
    public Map<String,String> translateForAdroid(
            @RequestParam(name="userEmail") String userEmail,
            @RequestParam(name="textToTranslate") String textToTranslate,
            @RequestParam(name="fromLanguage") String fromLanguage,
            @RequestParam(name="toLanguage") String toLanguage){

        String translatedText = textTranslator.translateText(textToTranslate,toLanguage,fromLanguage);

        TranslateHistory translateHistory = new TranslateHistory(userEmail, textToTranslate, translatedText, fromLanguage, toLanguage);
        translateHistoryRepo.addTranslateHistory(translateHistory);

        HashMap<String,String> res = new HashMap<>();
        res.put("translatedText",translatedText);
        return res;
    }

    @GetMapping("/history")
    public String history(){
        return "history";
    }


    @RequestMapping("/translateHistory/{email}")
    public @ResponseBody
    List<TranslateHistory> translateHistory(@PathVariable String email) throws ExecutionException, InterruptedException {
        System.out.println(email+" is send request to get history ------------------------");
        return translateHistoryRepo.getTranslateHistoryOfUser(email);
    }
}
