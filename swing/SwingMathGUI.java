package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import numeric.GCD;
import numeric.Factorial;
import numeric.NegativeFactorial;

/**
 * Swing wrapper GUI for GCD and Factorial utilities in the numeric package.
 */
public class SwingMathGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public SwingMathGUI() {
        super("Swing Math Tools");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set initial size to approximately half of the screen (bigger starting window)
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int w = Math.max(720, screen.width / 2);
        int h = Math.max(480, screen.height / 2);
        setSize(w, h);
        setLocation((screen.width - w) / 2, (screen.height - h) / 2);

        // Theme colors and font
        Color pastelBlue = new Color(196, 223, 255);
        Color pastelPink = new Color(255, 206, 228);
        Font themeFont = new Font("Segoe UI", Font.PLAIN, 16);

        // Apply background and label
        getContentPane().setBackground(pastelBlue);
        JLabel lbl = new JLabel("Choose Compute -> GCD or Factorial from the menu.", SwingConstants.CENTER);
        lbl.setForeground(new Color(60, 60, 80));
        lbl.setFont(themeFont.deriveFont(Font.PLAIN, 18f));
        lbl.setOpaque(false);
        add(lbl, BorderLayout.CENTER);

        // Save theme properties for menus and dialogs
        getRootPane().putClientProperty("theme.pastelBlue", pastelBlue);
        getRootPane().putClientProperty("theme.pastelPink", pastelPink);
        getRootPane().putClientProperty("theme.font", themeFont);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        Color pastelBlue = (Color) getRootPane().getClientProperty("theme.pastelBlue");
        if (pastelBlue == null) pastelBlue = new Color(196, 223, 255);
        menuBar.setBackground(pastelBlue);

        Font themeFont = (Font) getRootPane().getClientProperty("theme.font");
        if (themeFont == null) themeFont = new Font("SansSerif", Font.PLAIN, 16);

        // File menu with Exit
        JMenu fileMenu = new JMenu("File");
        fileMenu.setBackground(pastelBlue);
        fileMenu.setFont(themeFont);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(themeFont);
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Compute menu with GCD and Factorial
        JMenu computeMenu = new JMenu("Compute");
        computeMenu.setBackground(pastelBlue);
        computeMenu.setFont(themeFont);
        JMenuItem gcdItem = new JMenuItem("GCD");
        gcdItem.setFont(themeFont);
        gcdItem.addActionListener(e -> showGCDDialog());
        JMenuItem factItem = new JMenuItem("Factorial");
        factItem.setFont(themeFont);
        factItem.addActionListener(e -> showFactorialDialog());
        computeMenu.add(gcdItem);
        computeMenu.add(factItem);

        menuBar.add(fileMenu);
        menuBar.add(computeMenu);
        setJMenuBar(menuBar);
    }

    private void showGCDDialog() {
        GCDDialog dlg = new GCDDialog(this);
        dlg.setVisible(true);
    }

    private void showFactorialDialog() {
        FactorialDialog dlg = new FactorialDialog(this);
        dlg.setVisible(true);
    }

    // --- Custom modal dialogs ---
    private static class GCDDialog extends JDialog {
        private final JTextField firstField = new JTextField(12);
        private final JTextField secondField = new JTextField(12);

        GCDDialog(Frame owner) {
            super(owner, "Compute GCD", true);
            setLayout(new BorderLayout(8, 8));

            JPanel inputs = new JPanel(new GridLayout(2, 2, 6, 6));
            inputs.add(new JLabel("First integer:"));
            inputs.add(firstField);
            inputs.add(new JLabel("Second integer:"));
            inputs.add(secondField);
            add(inputs, BorderLayout.CENTER);

            // Theme
            Color pastelPink = null;
            Font themeFont = null;
            Window ow = getOwner();
            if (ow instanceof SwingMathGUI) {
                pastelPink = (Color) ((SwingMathGUI) ow).getRootPane().getClientProperty("theme.pastelPink");
                themeFont = (Font) ((SwingMathGUI) ow).getRootPane().getClientProperty("theme.font");
            }
            if (pastelPink == null) pastelPink = new Color(255, 206, 228);
            if (themeFont == null) themeFont = new Font("SansSerif", Font.PLAIN, 14);

            JPanel buttons = new JPanel();
            buttons.setBackground(pastelPink);
            JButton computeBtn = new JButton("Compute");
            JButton closeBtn = new JButton("Close");
            computeBtn.setBackground(new Color(255, 180, 200));
            closeBtn.setBackground(new Color(255, 180, 200));
            computeBtn.setFont(themeFont);
            closeBtn.setFont(themeFont);
            inputs.setBackground(((SwingMathGUI) owner).getContentPane().getBackground());
            add(inputs, BorderLayout.CENTER);
            buttons.add(computeBtn);
            buttons.add(closeBtn);
            add(buttons, BorderLayout.SOUTH);

            computeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int a = Integer.parseInt(firstField.getText().trim());
                        int b = Integer.parseInt(secondField.getText().trim());
                        int g = GCD.computeGCD(a, b);
                        JOptionPane.showMessageDialog(GCDDialog.this, "GCD(" + a + ", " + b + ") = " + g, "Result", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(GCDDialog.this, "Please enter valid integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(GCDDialog.this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            closeBtn.addActionListener(e -> dispose());

            pack();
            setLocationRelativeTo(owner);
        }
    }

    private static class FactorialDialog extends JDialog {
        private final JTextField field = new JTextField(12);

        FactorialDialog(Frame owner) {
            super(owner, "Compute Factorial", true);
            setLayout(new BorderLayout(8, 8));

            JPanel inputs = new JPanel(new GridLayout(1, 2, 6, 6));
            inputs.add(new JLabel("Non-negative integer:"));
            inputs.add(field);
            add(inputs, BorderLayout.CENTER);

            Color pastelPink = null;
            Font themeFont = null;
            Window ow = getOwner();
            if (ow instanceof SwingMathGUI) {
                pastelPink = (Color) ((SwingMathGUI) ow).getRootPane().getClientProperty("theme.pastelPink");
                themeFont = (Font) ((SwingMathGUI) ow).getRootPane().getClientProperty("theme.font");
            }
            if (pastelPink == null) pastelPink = new Color(255, 206, 228);
            if (themeFont == null) themeFont = new Font("SansSerif", Font.PLAIN, 14);

            JPanel buttons = new JPanel();
            buttons.setBackground(pastelPink);
            JButton computeBtn = new JButton("Compute");
            JButton closeBtn = new JButton("Close");
            computeBtn.setBackground(new Color(255, 180, 200));
            closeBtn.setBackground(new Color(255, 180, 200));
            computeBtn.setFont(themeFont);
            closeBtn.setFont(themeFont);
            add(buttons, BorderLayout.SOUTH);
            buttons.add(computeBtn);
            buttons.add(closeBtn);

            computeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int n = Integer.parseInt(field.getText().trim());
                        if (n < 0) {
                            throw new NegativeFactorial("Cannot compute factorial of a negative number.");
                        }
                        if (n > 170) {
                            throw new IllegalArgumentException("Input too large; result may overflow double. Please enter 0..170.");
                        }
                        double f = Factorial.computeFactorial(n);
                        JOptionPane.showMessageDialog(FactorialDialog.this, n + "! = " + f, "Result", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(FactorialDialog.this, "Please enter a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } catch (NegativeFactorial nf) {
                        JOptionPane.showMessageDialog(FactorialDialog.this, nf.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    } catch (IllegalArgumentException iae) {
                        JOptionPane.showMessageDialog(FactorialDialog.this, iae.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FactorialDialog.this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            closeBtn.addActionListener(e -> dispose());

            pack();
            setLocationRelativeTo(owner);
        }
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
