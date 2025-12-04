package com.candileasing.notificationservice.resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CssResource {

    @RequestMapping(value = "absenceSuccessionStyle.css", method = RequestMethod.GET)
    public String main(Model model) {
        return "absenceSuccessionStyle.css";
    }
}