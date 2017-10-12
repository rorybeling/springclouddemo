package src.main.java.skypackage.services;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "OOPS, 404!  ";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}