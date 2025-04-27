import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class pass {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[{]}|;:,.<>?";

    private static final String ALL_CHARACTERS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS;

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(new BorderLayout(10, 10));

        // Create a panel for the header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 94, 77));
        JLabel titleLabel = new JLabel("Password Generator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Create a panel for the password display
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout(5, 5));
        JLabel passwordLabel = new JLabel("Generated Password: ");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField passwordField = new JTextField(20);
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Arial", Font.BOLD, 16));
        passwordField.setBackground(new Color(245, 245, 245));
        passwordField.setHorizontalAlignment(JTextField.CENTER);

        displayPanel.add(passwordLabel, BorderLayout.NORTH);
        displayPanel.add(passwordField, BorderLayout.CENTER);

        // Create a panel for the password length selection
        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BorderLayout(5, 5));
        JLabel lengthLabel = new JLabel("Password Length: ");
        lengthLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JSlider lengthSlider = new JSlider(8, 20, 12);
        lengthSlider.setMajorTickSpacing(4);
        lengthSlider.setMinorTickSpacing(1);
        lengthSlider.setPaintTicks(true);
        lengthSlider.setPaintLabels(true);
        lengthSlider.setPreferredSize(new Dimension(250, 50));

        sliderPanel.add(lengthLabel, BorderLayout.NORTH);
        sliderPanel.add(lengthSlider, BorderLayout.CENTER);

        // Create a panel for the generate button
        JPanel buttonPanel = new JPanel();
        JButton generateButton = new JButton("Generate Password");
        generateButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateButton.setBackground(new Color(255, 94, 77));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        generateButton.setBorder(BorderFactory.createCompoundBorder(generateButton.getBorder(), BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        generateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Action listener for button click
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = lengthSlider.getValue();
                String password = generatePassword(length);
                passwordField.setText(password);
            }
        });

        buttonPanel.add(generateButton);

        // Add panels to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(displayPanel, BorderLayout.CENTER);
        frame.add(sliderPanel, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        // Set frame visible
        frame.setVisible(true);
    }

    // Function to generate a password of given length
    private static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALL_CHARACTERS.length());
            password.append(ALL_CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }
}
