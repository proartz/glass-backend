package pl.oncode.glass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

import static java.util.TimeZone.getAvailableIDs;

@SpringBootApplication
public class GlassApplication {

    Logger logger = LoggerFactory.getLogger(GlassApplication.class);

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(GlassApplication.class, args);
    }

    @PostConstruct
    public void init() {
        logger.debug(String.valueOf(TimeZone.getDefault()));
        logger.debug(env.getProperty("spring.jackson.time-zone"));
        logger.debug(String.valueOf(new Date()));
    }

}