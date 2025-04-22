import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Map;

class CurrencyConverterGUI extends JFrame {

    private static final String API_KEY = "78df7551567221d50562b1f6"; // Replace with your real API key
    private static final String API_BASE = "https://v6.exchangerate-api.com/v6/";

    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JTextField amountField;
    private JLabel resultLabel;

    public CurrencyConverterGUI() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(6, 1, 10, 5));

        String[] currencies = {"USD", "EUR", "GBP", "INR", "CAD", "AUD", "JPY", "CNY"};

        fromCurrencyBox = new JComboBox<>(currencies);
        toCurrencyBox = new JComboBox<>(currencies);
        amountField = new JTextField();
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result will appear here", SwingConstants.CENTER);

        add(new JLabel("From Currency:"));
        add(fromCurrencyBox);
        add(new JLabel("To Currency:"));
        add(toCurrencyBox);
        add(new JLabel("Amount:"));
        add(amountField);
        add(convertButton);
        add(resultLabel);

        convertButton.addActionListener(this::performConversion);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void performConversion(ActionEvent e) {
        String fromCurrency = (String) fromCurrencyBox.getSelectedItem();
        String toCurrency = (String) toCurrencyBox.getSelectedItem();
        String amountText = amountField.getText();

        try {
            double amount = Double.parseDouble(amountText);
            double rate = getExchangeRate(fromCurrency, toCurrency);
            double converted = amount * rate;

            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, converted, toCurrency));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid amount entered.");
        } catch (Exception ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    public double getExchangeRate(String base, String target) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = API_BASE + API_KEY + "/latest/" + base;

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("API request failed");
            }

            String json = response.body().string();
            Gson gson = new Gson();
            ExchangeResponse data = gson.fromJson(json, ExchangeResponse.class);

            if (!data.conversion_rates.containsKey(target)) {
                throw new IllegalArgumentException("Invalid target currency");
            }

            return data.conversion_rates.get(target);
        }
    }

    static class ExchangeResponse {
        Map<String, Double> conversion_rates;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverterGUI::new);
    }
}
