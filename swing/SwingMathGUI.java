package swing;

import javax.swing.*;
import java.awt.*;

/**
 * Swing wrapper GUI for GCD and Factorial utilities in this package.
 */
public class SwingMathGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public SwingMathGUI() {
        super("Swing Math Tools");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 160);
        setLocationRelativeTo(null);
        createMenuBar();
        // Minimal content so the window isn't empty
        JLabel lbl = new JLabel("Choose Compute -> GCD or Factorial from the menu.", SwingConstants.CENTER);
        add(lbl, BorderLayout.CENTER);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu with Exit
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Compute menu with GCD and Factorial
        JMenu computeMenu = new JMenu("Compute");
        JMenuItem gcdItem = new JMenuItem("GCD");
        gcdItem.addActionListener(e -> showGCDDialog());
        JMenuItem factItem = new JMenuItem("Factorial");
        factItem.addActionListener(e -> showFactorialDialog());
        computeMenu.add(gcdItem);
        computeMenu.add(factItem);

        menuBar.add(fileMenu);
        menuBar.add(computeMenu);
        setJMenuBar(menuBar);
    }

    private void showGCDDialog() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 6, 6));
        panel.add(new JLabel("First integer:"));
        JTextField firstField = new JTextField();
        panel.add(firstField);
        panel.add(new JLabel("Second integer:"));
        JTextField secondField = new JTextField();
        panel.add(secondField);

        int option = JOptionPane.showConfirmDialog(this, panel, "Compute GCD", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int a = parseInteger(firstField.getText());
                int b = parseInteger(secondField.getText());
                int g = GCD.computeGCD(a, b);
                JOptionPane.showMessageDialog(this, "GCD(" + a + ", " + b + ") = " + g, "Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter valid integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showFactorialDialog() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 6, 6));
        panel.add(new JLabel("Non-negative integer:"));
        JTextField field = new JTextField();
        panel.add(field);

        int option = JOptionPane.showConfirmDialog(this, panel, "Compute Factorial", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int n = parseInteger(field.getText());
                if (n < 0) {
                    throw new IllegalArgumentException("Cannot compute factorial of a negative number.");
                }
                // Factorial uses double; values above 170 will overflow double. Protect user.
                if (n > 170) {
                    throw new IllegalArgumentException("Input too large; result may overflow double. Please enter 0..170.");
                }
                double f = Factorial.computeFactorial(n);
                JOptionPane.showMessageDialog(this, n + "! = " + f, "Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, iae.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int parseInteger(String s) throws NumberFormatException {
        if (s == null || s.trim().isEmpty()) {
            throw new NumberFormatException("Empty input");
        }
        return Integer.parseInt(s.trim());
    }

    public static void main(String[] args) {
        // Detect headless environments early and exit with a clear message.
        if (GraphicsEnvironment.isHeadless()) {
            System.err.println("Headless environment detected: GUI not available.");
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            SwingMathGUI gui = new SwingMathGUI();
            gui.setVisible(true);
        });
    }
}
