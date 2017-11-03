package be.cegeka.ivolunteer.event.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/health")
public class StatusResource {

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public String getStatus(){
        return "up";
    }
}
