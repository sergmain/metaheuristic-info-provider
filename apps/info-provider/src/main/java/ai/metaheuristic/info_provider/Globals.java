package ai.metaheuristic.info_provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

import javax.annotation.PostConstruct;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Sergio Lissner
 * Date: 7/3/2022
 * Time: 11:27 PM
 */
@ConfigurationProperties("mh.info")
@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
public class Globals {

    @Setter
    public static class ThreadNumber {
        private int scheduler = 3;

        public int getScheduler() {
            return minMax( scheduler, 3, 6);
        }
    }

    public final ThreadNumber threadNumber = new ThreadNumber();

    public boolean sslRequired = false;
    public boolean testing = false;

    @SneakyThrows
    @PostConstruct
    public void postConstruct() {
        logGlobals();
        logSystemEnvs();
        logGarbageCollectors();
    }

    @Nullable
    private static Path toPath(@Nullable String dirAsString) {
        if (dirAsString==null || dirAsString.isBlank()) {
            return null;
        }

        // special case for ./some-dir
        if (dirAsString.charAt(0) == '.' && (dirAsString.charAt(1) == '\\' || dirAsString.charAt(1) == '/')) {
            return Path.of(dirAsString.substring(2));
        }
        return Path.of(dirAsString);
    }

    public static int minMax(int curr, int min, int max) {
        if (curr >=min && curr <=max) {
            return curr;
        }
        else if (curr <min) {
            return min;
        }
        return max;
    }

    private static void logSystemEnvs() {
        log.info("Current system properties:");
        System.getProperties().forEach( (o, o2) -> {
            if (o instanceof String) {
                if (StringUtils.equalsAny((String)o, "java.class.path", "java.library.path", "line.separator")) {
                    return;
                }
            }
            log.info("'\t{}: {}", o, o2);
        });
    }

    private void logGlobals() {
        final Runtime rt = Runtime.getRuntime();
        log.warn("Memory, free: {}, max: {}, total: {}", rt.freeMemory(), rt.maxMemory(), rt.totalMemory());
        log.info("Current globals:");
        log.info("'\ttesting: {}", testing);
        log.info("'\tthreadNumber.scheduler: {}", threadNumber.getScheduler());
    }

    private static void logGarbageCollectors() {
        log.info("Garbage collectors:");
        List<GarbageCollectorMXBean> beans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean : beans) {
            log.info("'\t"+ bean.getName());
        }
    }


}
