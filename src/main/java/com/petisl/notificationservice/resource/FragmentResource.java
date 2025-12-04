package com.candileasing.notificationservice.resource;

import com.candileasing.notificationservice.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FragmentResource {

    @GetMapping("/absence-succession")
    public String absenceSuccessionFragment() {
        return "absence_succession.html";
    }

    @GetMapping("/add-event")
    public String addEvent() {
        return "add_admin_event.html";
    }

    @GetMapping("/birthday")
    public ModelAndView birthday() {

        ModelAndView modelAndView = new ModelAndView("birthday");

        Employee employee1 = new Employee("Israel", "zraelwalker@gmail.com", "staff111");
        Employee employee2 = new Employee("Walker", "walkerzmail@gmail.com", "staff222");

        List<Employee> employees = List.of(employee1, employee2);
        modelAndView.addObject("employees", employees);

        return modelAndView;
    }
}
