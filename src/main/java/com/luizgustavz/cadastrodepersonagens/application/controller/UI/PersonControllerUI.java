package com.luizgustavz.cadastrodepersonagens.application.controller.UI;

import com.luizgustavz.cadastrodepersonagens.application.dto.response.PersonResponse;
import com.luizgustavz.cadastrodepersonagens.infrastructure.services.PersonServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ui/person")
public class PersonControllerUI {

    private final PersonServices services;

    public PersonControllerUI(
            PersonServices services
    ) {
        this.services = services;
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
