package collections;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamMapDemo {

    static class GymTeam {
        String teamName;
        List<String> trainerNames;

        public GymTeam(String name, List<String> trainers) {
            this.teamName = name;
            this.trainerNames = trainers;
        }

        public List<String> getTrainerNames(){
            return this.trainerNames;
        }

        public Stream<String> getTrainerNamesStream(){
            return this.trainerNames.stream();
        }
    }

    public static void main(String[] args) {
        List<GymTeam> gymBranches = Arrays.asList(
                new GymTeam("Sanpada Branch", Arrays.asList("Alice", "Bob")),
                new GymTeam("Vashi Branch", Arrays.asList("Charlie", "David"))
        );

        // 🗺️ 1. Using MAP: Results in a nested, messy structure
        // Returns: List<List<String>>
        List<List<String>> nestedMapResult = gymBranches.stream()
                .map(branch -> branch.trainerNames)
                .toList();

        System.out.println("nestedMapResult Roster: " + nestedMapResult);
        // 🗺️ 2. Using FLATMAP: Flattens the nested lists into a single flat layer
        // Returns: List<String> -> ["Alice", "Bob", "Charlie", "David"]
        List<String> flatMapResult = gymBranches.stream()
                .flatMap(GymTeam::getTrainerNamesStream) // Opens the inner collection
                .toList();

        System.out.println("FlatMap Roster: " + flatMapResult);
    }
}
