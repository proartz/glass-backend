package pl.oncode.glass.config;

import java.util.HashSet;
import java.util.Set;

public class StageOneOperations implements StageOperations {

    private Set<String> operations;

    public StageOneOperations() {
        operations = new HashSet<>();
        operations.add("Cięcie");
        operations.add("Szlifowanie");
        operations.add("Wiercenie");
        operations.add("CNC");
    }

    @Override
    public Set<String> getOperations() {
        return operations;
    }
}
