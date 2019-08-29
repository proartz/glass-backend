package pl.oncode.glass.config;

import java.util.HashSet;
import java.util.Set;

public class StageTwoOperations implements StageOperations {

    private Set<String> operations;

    public StageTwoOperations() {
        operations = new HashSet<>();
        operations.add("Hardening");
        operations.add("Enamelling");
        operations.add("Lamination");
    }

    @Override
    public Set<String> getOperations() {
        return operations;
    }
}
