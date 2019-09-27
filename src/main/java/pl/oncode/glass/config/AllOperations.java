package pl.oncode.glass.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllOperations implements StageOperations {
    private List<String> operations;

    public AllOperations() {
        operations = new ArrayList<>();
        operations.add("Cięcie");
        operations.add("Szlifowanie");
        operations.add("Wiercenie");
        operations.add("CNC");
        operations.add("Hartowanie");
        operations.add("Emaliowanie");
        operations.add("Laminowanie");
        operations.add("Wydanie");
    }

    @Override
    public List<String> getOperations() {
        return operations;
    }
}
