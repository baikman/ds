### Project 2

Develop a GUI to wrap the GCD and Factorial classes from Project #1 with the Swing toolkit for Java.

- GCD: compute the greatest common divisor of two integers.
- Factorial: compute the factorial of a non-negative integer (uses double; inputs above 170 may overflow).

Files
- `SwingMathGUI.java` — top-level JFrame with a menu bar (File and Compute). Choose Compute -> GCD or Factorial to open modal dialogs for input and computation.

Usage
1. Compile the sources: `javac numeric/*.java swing/*.java`
2. Run the GUI: `java -cp . swing.SwingMathGUI`

Notes
- The GUI applies a light blue/pink theme and attempts to use a nicer system font when available.
- The factorial implementation yields a `double`.