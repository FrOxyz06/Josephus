# Josephus Simulation

An interactive Java simulation of the Josephus elimination problem using a circular singly linked list. Names form a circle; every k-th remaining person is removed until one survives.

## Run

Use a JDK (Java 21 is used in CI). From the repository root:

```sh
javac --release 21 -d build *.java
java -cp build JosephusDriver
```

Press Enter for each elimination. `people.txt` contains sample names, one per line; any whitespace separates names. Keep at least one name in the file.

The default constructor chooses a random positive count from 1 through half the group size, with a minimum of 1. To reproduce a scenario in code, use `new JosephusSim("people.txt", 3)`.

## Check

```sh
java -cp build ProjectTest
```

The fixed seven-person, count-three example ends with Drew. Regression checks cover the full removal sequence, repeated display, elimination before display, one/two-person inputs, empty input, and invalid counts. GitHub Actions compiles and runs them.

## Layout

- `JosephusDriver.java`: interactive entry point.
- `JosephusSim.java`: circular list and elimination.
- `PersonNode.java`: linked-list node.
- `people.txt`: runnable sample input.
- `ProjectTest.java`: deterministic regression checks.

Each elimination counts from the next remaining person. Displaying the circle does not change it. Dependabot checks workflow updates weekly.
