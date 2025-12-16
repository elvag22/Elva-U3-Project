import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

public class PasswordGenerator {
    private JFrame frame;
    private JTextField passwordChoices;
    private JButton generateButton, chartButton;
    private ArrayList<String> passwords = new ArrayList<>();
    private ArrayList<Integer> lengths = new ArrayList<>();
    private Random rand = new Random();

    public PasswordGenerator() {
        createGUI()
    }

    private void createGUI() {
        frame = new JFrame("Password Generator!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new FlowLayout());

        JLabel passwordLabel = new JLabel("Your Password:");
        frame.add(passwordLabel);

        passwordChoices = new JTextField(20);
        passwordChoices.setEditable = (false);
        frame.add(passwordChoices);

        generateButton = new JButton("Click to make password!");
        frame.add(generateButton);

        private void passwordLengthChoice() {
            String input = JOptionPane.showInputDialog(frame, "Enter how long you want your password to be as an integer");
            if (input == null) return;
            try {
                int length = Integer.parseInt(input.trim());
                if (length <= 0) {
                    JOptionPane.showMessageDialog(frame, "Please enter a positive integer!");
                    return;
                }
                

}
