import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class JosephusSim {
    private PersonNode circle;
    private PersonNode previous;
    private int size;
    private final int eliminationCount;

    public JosephusSim(String fileName) {
        this(fileName, null);
    }

    /** A fixed count makes examples and tests reproducible. */
    public JosephusSim(String fileName, int count) {
        this(fileName, Integer.valueOf(count));
    }

    private JosephusSim(String fileName, Integer count) {
        try (Scanner file = new Scanner(new File(fileName))) {
            while (file.hasNext()) {
                PersonNode current = new PersonNode(file.next());
                if (circle == null) {
                    circle = current;
                } else {
                    previous.next = current;
                }
                previous = current;
                size++;
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Cannot read " + fileName, e);
        }
        if (size == 0) {
            throw new IllegalArgumentException("The people file must not be empty.");
        }
        if (count != null && count < 1) {
            throw new IllegalArgumentException("Elimination count must be positive.");
        }
        previous.next = circle;
        eliminationCount = count == null ? 1 + new Random().nextInt(Math.max(1, size / 2)) : count;
        System.out.println("=== Elimination count is " + eliminationCount + " ===");
    }

    public void eliminate() {
        if (isOver()) {
            return;
        }
        for (int i = 1; i < eliminationCount; i++) {
            previous = previous.next;
        }
        PersonNode eliminated = previous.next;
        System.out.println("Eliminated: " + eliminated.name);
        previous.next = eliminated.next;
        circle = previous.next;
        size--;
    }

    public boolean isOver() {
        return size == 1;
    }

    public String toString() {
        if (isOver()) {
            return "Last survivor: " + circle.name;
        }
        StringBuilder result = new StringBuilder();
        PersonNode current = circle;
        for (int index = 1; index <= size; index++) {
            if (index > 1) result.append(' ');
            result.append(index).append('-').append(current.name);
            current = current.next;
        }
        return result.toString();
    }
}
