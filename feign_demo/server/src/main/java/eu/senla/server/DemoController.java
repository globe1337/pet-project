package eu.senla.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static eu.senla.server.SecureStatus.*;

@RestController
public class DemoController {
    @GetMapping("multiply")
    public DemoResponse getResponse(@RequestParam Integer param1, @RequestParam Integer param2, HttpServletRequest httpRequest) {
        SecureStatus status = NOT_SECURED;
        if (httpRequest.isSecure()) {
            status = SECURED;
        }
        String message = String.format("%s multiply by %s equals %s", param1, param2, param1 * param2);
        return new DemoResponse(status, message);
    }
}
