package ir.reyhaneh.hotelreservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping("/")
    public String greeting() {
        return "Hello! I am Reyhaneh Jabbari 09911671428";
    }

}
