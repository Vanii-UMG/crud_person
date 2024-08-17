package gt.edu.umg.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greatings")
public class GreatingController {

    @GetMapping
    public String greeting() {
        return "Hola Mundo";
    }


}
