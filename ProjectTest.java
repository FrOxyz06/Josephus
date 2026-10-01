import java.nio.file.Files;
import java.nio.file.Path;

public class ProjectTest {
    public static void main(String[] args) throws Exception {
        JosephusSim simulation = new JosephusSim("people.txt", 3);
        String before = simulation.toString();
        if (!before.equals(simulation.toString())) throw new AssertionError("Display mutated the circle");
        String[] expected = {
            "1-Drew 2-Ellis 3-Finley 4-Gray 5-Alex 6-Blair",
            "1-Gray 2-Alex 3-Blair 4-Drew 5-Ellis",
            "1-Drew 2-Ellis 3-Gray 4-Alex",
            "1-Alex 2-Drew 3-Ellis",
            "1-Alex 2-Drew",
            "Last survivor: Drew"
        };
        for (String result : expected) {
            simulation.eliminate();
            if (!result.equals(simulation.toString())) throw new AssertionError(simulation);
        }
        if (!simulation.isOver()) throw new AssertionError("Simulation did not finish");
        simulation.eliminate();
        if (!"Last survivor: Drew".equals(simulation.toString())) throw new AssertionError("Survivor changed");
        Path input = Files.createTempFile("josephus-test", ".txt");
        try {
            Files.writeString(input, "Only");
            if (!new JosephusSim(input.toString()).isOver()) throw new AssertionError("Single person");
            Files.writeString(input, "One Two");
            JosephusSim pair = new JosephusSim(input.toString(), 1);
            pair.eliminate(); // Must work without calling toString first.
            if (!"Last survivor: Two".equals(pair.toString())) throw new AssertionError(pair);
            try {
                new JosephusSim(input.toString(), 0);
                throw new AssertionError("Accepted zero count");
            } catch (IllegalArgumentException expectedError) { }
            Files.writeString(input, "");
            try {
                new JosephusSim(input.toString());
                throw new AssertionError("Accepted empty input");
            } catch (IllegalArgumentException expectedError) { }
        } finally {
            Files.deleteIfExists(input);
        }
        System.out.println("Elimination sequence and edge cases passed.");
    }
}
