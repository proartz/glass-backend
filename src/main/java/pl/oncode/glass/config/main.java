package pl.oncode.glass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class main {

    @Bean
    public StageOneOperations stageOneOperations() {
        Set<String> stageOneOperations = new HashSet<>();
        stageOneOperations.add("Cutting");
        stageOneOperations.add("Sanding");
        stageOneOperations.add("Drilling");
        stageOneOperations.add("CNC");

        return () -> stageOneOperations;
    }

    @Bean
    public StageTwoOperations getStageTwoOperations() {
        Set<String> stageTwoOperations = new HashSet<>();
        stageTwoOperations.add("Hardening");
        stageTwoOperations.add("Enamelling");
        stageTwoOperations.add("Lamination");

        return () -> stageTwoOperations;
    }
}
