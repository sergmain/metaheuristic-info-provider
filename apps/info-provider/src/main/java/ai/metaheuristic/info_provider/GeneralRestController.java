package ai.metaheuristic.info_provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author Serge
 * Date: 12/18/2021
 * Time: 3:51 AM
 */
@RestController
@RequestMapping("/rest/v1")
@Slf4j
@CrossOrigin
@RequiredArgsConstructor
public class GeneralRestController {

    @GetMapping("/info")
    public String info() {
        return "ok";
    }

}
