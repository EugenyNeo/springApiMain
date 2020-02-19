package com.company.springApiMain.controller;

import com.company.springApiMain.domain.Person;
import com.company.springApiMain.domain.User;
import com.company.springApiMain.repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private PersonRepo personRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(
            Map<String, Object> model
    ) {
            return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model){
        Iterable<Person> persons = personRepo.findAll();

        if(filter !=null && !filter.isEmpty()){
            persons = personRepo.findByPersonId(filter);
        }else{
            persons = personRepo.findAll();
        }
        model.addAttribute("persons", persons);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String personId,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String dateOfBirth,
            @RequestParam String familyStatus,
            @RequestParam String education, Map<String, Object> model,
            @RequestParam("file")MultipartFile file
    ) throws IOException {
        Person person = new Person( personId, firstName, lastName, dateOfBirth, familyStatus, education , user);

        if(file !=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            person.setFirstName(resultFilename);
        }
        personRepo.save(person);

        Iterable<Person> persons = personRepo.findAll();
        model.put("persons", persons);
        return "main";

    }


}