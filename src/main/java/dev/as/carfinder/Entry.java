package dev.as.carfinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Entry {
    @GetMapping
    public String entry() {
        return """
                <h1>Welcome to CarFinder Backend API.</h1>
                                    <p>Visit here for <a href="https://carfinder-894g.onrender.com/swagger-ui/index.html" target="_blank">Documentation</a></p>
                """;
    }
}
