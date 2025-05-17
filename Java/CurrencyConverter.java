import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.*;

class CurrencyConverter {
    private JTextField amountField;
    private JComboBox<String> baseCurrencyComboBox, targetCurrencyComboBox;
    private JLabel resultLabel, feedbackLabel;

    private final String[] currencies = {
        "INR", "USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "SEK"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter().createUI());
    }

    private void createUI() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(54, 57, 63));

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBackground(new Color(54, 57, 63));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(createLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        panel.add(createLabel("From Currency:"));
        baseCurrencyComboBox = new JComboBox<>(currencies);
        panel.add(baseCurrencyComboBox);

        panel.add(createLabel("To Currency:"));
        targetCurrencyComboBox = new JComboBox<>(currencies);
        panel.add(targetCurrencyComboBox);

        JButton convertButton = new JButton("Convert");
        convertButton.setBackground(new Color(255, 193, 7));
        convertButton.addActionListener(this::convertCurrency);
        panel.add(convertButton);

        resultLabel = createLabel("");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(resultLabel);

        feedbackLabel = createLabel("");
        feedbackLabel.setForeground(Color.RED);
        panel.add(feedbackLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private void convertCurrency(ActionEvent e) {
        try {
            String base = (String) baseCurrencyComboBox.getSelectedItem();
            String target = (String) targetCurrencyComboBox.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + base;
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) json.append(line);
            reader.close();

            String targetRateString = json.toString().split("\"" + target + "\":")[1].split(",")[0].replace("}", "").trim();
            double rate = Double.parseDouble(targetRateString);
            double result = amount * rate;

            resultLabel.setText(String.format("%.2f %s", result, getCurrencySymbol(target)));
            feedbackLabel.setText("");
        } catch (Exception ex) {
            resultLabel.setText("");
            feedbackLabel.setText("Invalid input or network error.");
        }
    }

    private String getCurrencySymbol(String code) {
        return switch (code) {
            case "INR" -> "₹";
            case "USD" -> "$";
            case "EUR" -> "€";
            case "GBP" -> "£";
            case "JPY" -> "¥";
            case "AUD" -> "A$";
            case "CAD" -> "C$";
            case "CHF" -> "CHF";
            case "CNY" -> "¥";
            case "SEK" -> "kr";
            default -> code;
        };
    }
}
