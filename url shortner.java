import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class url {
    private static final String BASE_URL = "http://localhost:8080/";
    private static final int SHORT_URL_LENGTH = 6;
    
    // A HashMap to store the mapping of short URL to original URL
    private static final HashMap<String, String> urlMap = new HashMap<>();

    // Method to generate a random short URL
    public static String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();

        // Generate a 6-character random string
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }

        return BASE_URL + shortUrl.toString();
    }

    // Method to shorten the URL and store the mapping
    public static String shortenUrl(String originalUrl) {
        String shortUrl = generateShortUrl();
        urlMap.put(shortUrl, originalUrl);
        return shortUrl;
    }

    // Method to get the original URL from the shortened URL
    public static String getOriginalUrl(String shortUrl) {
        return urlMap.get(shortUrl);
    }

    // Swing UI for the URL shortener
    public static void main(String[] args) {
        // Set up the JFrame for the UI
        JFrame frame = new JFrame("URL Shortener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());

        // Create components for the UI
        JLabel inputLabel = new JLabel("Enter URL to Shorten:");
        JTextField urlInputField = new JTextField(25);
        JButton shortenButton = new JButton("Shorten URL");
        JLabel resultLabel = new JLabel("Shortened URL: ");
        JTextField resultField = new JTextField(25);
        resultField.setEditable(false);

        // Add components to the frame
        frame.add(inputLabel);
        frame.add(urlInputField);
        frame.add(shortenButton);
        frame.add(resultLabel);
        frame.add(resultField);

        // Add action listener for button click
        shortenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String originalUrl = urlInputField.getText();
                if (!originalUrl.isEmpty()) {
                    // Shorten the URL and display it
                    String shortenedUrl = shortenUrl(originalUrl);
                    resultField.setText(shortenedUrl);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid URL", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}
