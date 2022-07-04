package ai.metaheuristic.info_provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Serge
 * Date: 12/18/2021
 * Time: 3:51 AM
 */
@RestController
@RequestMapping("/rest/v1")
@Slf4j
@Profile("server")
@CrossOrigin
@RequiredArgsConstructor
public class GeneralRestController {


    @PostMapping("/info")
    public String editFormCommit(Long id, String publicName, boolean enabled, Authentication authentication) {
        return "ok";
    }
}
