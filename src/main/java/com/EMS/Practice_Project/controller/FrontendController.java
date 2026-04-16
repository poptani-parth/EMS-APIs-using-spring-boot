package com.EMS.Practice_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping({"/", "/app", "/app/**"})
    public String index() {
        return "index";
    }
}

