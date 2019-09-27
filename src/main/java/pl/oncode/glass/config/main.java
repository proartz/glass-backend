package pl.oncode.glass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class main {

    @Bean StageOperations allOperations() {
        return new AllOperations();
    }
}
