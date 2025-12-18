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

    // sets GUI to make it visible
    public PasswordGenerator() {
        createGUI();
        frame.setVisible(true);
    }

    //Creates maoin GUI
    private void createGUI() {
        frame = new JFrame("Password Generator!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new FlowLayout());
//label for password display when generated
        JLabel passwordLabel = new JLabel("Your Password:");
        frame.add(passwordLabel);
//non user-ediatble field for password display
        passwordChoices = new JTextField(20);
        passwordChoices.setEditable(false);
        frame.add(passwordChoices);
//button to generate password
        generateButton = new JButton("Click to make password!");
        frame.add(generateButton);
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordLengthChoice();//ask user for password length
            }
        });
        //button to display charts of password lengths
        chartButton = new JButton("Show password lengths");
        frame.add(chartButton);
        chartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showChart();

            }
        });

    }

    //asks user for desired password length with conditionals to catch errors
    private void passwordLengthChoice() {
        String input = JOptionPane.showInputDialog(frame, "Enter how long you want your password to be as an integer");
        if (input == null) return;
        try {
            int length = Integer.parseInt(input.trim());
            if (length <= 0) {
                JOptionPane.showMessageDialog(frame, "Please enter a positive integer!");
                return;
            }
            String password = generatePassword(length);
            passwordChoices.setText(password);
            passwords.add(password);
            lengths.add(length);
            //saves password in csv
            try {
                savePassword(password, length);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving password: " + ex.getMessage());
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
        }
    }

    //pool of chars to choose from
    private String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String password = "";
        for (int i = 0; i < length; i++) {
            password += chars.charAt(rand.nextInt(chars.length()));
        }
        return password;

    }

    //This function was written by ChatGPT (OpenAI GPT-5)
    // Accessed on: 2025-12-16
    private void savePassword(String password, int length) throws IOException {
        File file = new File("passwords.csv");
        boolean newFile = file.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (newFile) {
                writer.write("Password,Length");
                writer.newLine();
            }
            writer.write(password + "," + length);
            writer.newLine();
        }
        //ChatGPT entry ends hyere
    }

    //creates a bar chart of every password generated so far
    private void showChart() {
        if (lengths.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No passwords are made yet!");
            return;
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < passwords.size(); i++) {
            //add data for chart
            dataset.addValue(lengths.get(i), "Passoword Length", "Password Number" + (i + 1));
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Password Lengths,", // title
                "Password", // x axis
                "Length", // y axis
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );
//display chart in a new frame

        ChartFrame chartFrame = new ChartFrame("Password Length Chart", chart);
        chartFrame.setSize(500, 400);
        chartFrame.setVisible(true);


    }

    public static void main(String[] args) {
        new PasswordGenerator();
    }
}


