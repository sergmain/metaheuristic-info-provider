package ai.metaheuristic.info_provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Serge
 * Date: 12/18/2021
 * Time: 3:50 AM
 */
@SpringBootApplication
@Slf4j
public class MetaheuristicInfoProviderApplication {

    public static void main(String[] args) {
        final String encoding = System.getProperty("file.encoding");
        if (!StringUtils.equalsAnyIgnoreCase(encoding, "utf8", "utf-8")) {
            System.out.println("Must be run with -Dfile.encoding=UTF-8, actual file.encoding: " + encoding);
            System.exit(-1);
        }
        SpringApplication.run(MetaheuristicInfoProviderApplication.class, args);
    }
}
