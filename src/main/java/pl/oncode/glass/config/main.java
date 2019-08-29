package pl.oncode.glass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class main {

    @Bean
    public StageOneOperations stageOneOperations() {
        return new StageOneOperations();
    }

    @Bean
    public StageTwoOperations getStageTwoOperations() {
        return new StageTwoOperations();
    }
}
