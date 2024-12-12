
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

public class Expenses {

    private JFrame frame;
    private JComboBox<String> allowanceComboBox, incomeComboBox, foodComboBox, transportComboBox, entertainmentComboBox, schoolComboBox, otherComboBox, savingsGoalComboBox;
    private JTextField allowanceTextField, incomeTextField, foodTextField, transportTextField, entertainmentTextField, schoolTextField, otherTextField, savingsGoalTextField;
    private JTextArea resultArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Expenses().createGUI());
    }

    public void createGUI() {
        frame = new JFrame("Weekly Allowance Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        // Use GridBagLayout for better control over component layout
        frame.setLayout(new BorderLayout());

        // Panel for user inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Adding combo boxes and text fields for inputs
        addInputComponent(inputPanel, "Select Weekly Allowance: ", gbc, 0, allowanceComboBox = createAmountComboBox(), allowanceTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Additional Income: ", gbc, 1, incomeComboBox = createAmountComboBox(), incomeTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Food Expenses: ", gbc, 2, foodComboBox = createExpenseComboBox(), foodTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Transport Expenses: ", gbc, 3, transportComboBox = createExpenseComboBox(), transportTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Entertainment Expenses: ", gbc, 4, entertainmentComboBox = createExpenseComboBox(), entertainmentTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select School Expenses: ", gbc, 5, schoolComboBox = createExpenseComboBox(), schoolTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Additional Expenses: ", gbc, 6, otherComboBox = createExpenseComboBox(), otherTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Savings Goal: ", gbc, 7, savingsGoalComboBox = createAmountComboBox(), savingsGoalTextField = new JTextField(10));

        // Button to trigger the calculation
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setPreferredSize(new Dimension(100, 25));  // Set a fixed size for the button
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        inputPanel.add(calculateButton, gbc);

        // Button to reset all inputs
        JButton resetButton = new JButton("Reset");
        gbc.gridx = 3;
        inputPanel.add(resetButton, gbc);

        // Scrollable area to show results
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add components to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add event listener for the calculate button
        calculateButton.addActionListener(e -> calculateExpenses());

        // Add event listener for the reset button
        resetButton.addActionListener(e -> resetInputs());

        // Pack frame to adjust the size based on content
        frame.pack();
        frame.setVisible(true);
    }

    // Helper method to add combo box and text field input components to the panel
    private void addInputComponent(JPanel panel, String labelText, GridBagConstraints gbc, int gridY, JComboBox<String> comboBox, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = gridY;
        panel.add(new JLabel(labelText), gbc);

        // Combo box or text field based on user selection
        gbc.gridx = 1;
        panel.add(comboBox, gbc);

        // Option for manual entry
        gbc.gridx = 2;
        panel.add(textField, gbc);
        textField.setVisible(false);  // Initially, hide the text field

        // Add action listener to combo box to toggle text field visibility
        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            textField.setVisible("Manual".equals(selected));
            panel.revalidate();
            panel.repaint();
        });
    }

    // Method to get value from combo box or text field (manual input)
    private double getInputValue(JComboBox<String> comboBox, JTextField textField) {
        String value = comboBox.getSelectedItem().toString();
        if ("Manual".equals(value)) {
            // If "Manual" is selected, use the text field input
            return Double.parseDouble(textField.getText().isEmpty() ? "0" : textField.getText());
        } else {
            return Double.parseDouble(value);
        }
    }

    // Create JComboBox for income and savings goals (100, 200, ..., 5000)
    private JComboBox<String> createAmountComboBox() {
        String[] options = new String[51];
        options[0] = "Manual";  // Replace the first value with "Manual"
        for (int i = 1; i <= 50; i++) {
            options[i] = String.valueOf(i * 100);  // Values from 100, 200, ..., 5000
        }
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setEditable(true);  // Allow user to type values
        return comboBox;
    }

    // Create JComboBox for expenses (food, transport, etc.)
    private JComboBox<String> createExpenseComboBox() {
        String[] options = new String[51];
        options[0] = "Manual";  // Replace the first value with "Manual"
        for (int i = 1; i <= 50; i++) {
            options[i] = String.valueOf(i * 100);  // Values from 100, 200, ..., 5000
        }
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setEditable(true);  // Allow user to type values
        return comboBox;
    }

    // Method to handle the calculation of expenses
    private void calculateExpenses() {
        try {
            // Retrieve selected or manually typed values
            double allowance = getInputValue(allowanceComboBox, allowanceTextField);
            double additionalIncome = getInputValue(incomeComboBox, incomeTextField);
            double totalIncome = allowance + additionalIncome;

            double foodExpenses = getInputValue(foodComboBox, foodTextField);
            double transportExpenses = getInputValue(transportComboBox, transportTextField);
            double entertainmentExpenses = getInputValue(entertainmentComboBox, entertainmentTextField);
            double schoolExpenses = getInputValue(schoolComboBox, schoolTextField);
            double otherExpenses = getInputValue(otherComboBox, otherTextField);
            double totalExpenses = foodExpenses + transportExpenses + entertainmentExpenses + schoolExpenses + otherExpenses;

            // Calculate remaining allowance
            double remainingAllowance = totalIncome - totalExpenses;

            // Currency formatting for Peso (PHP)
            NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
            String balanceMessage = remainingAllowance >= 0
                    ? "You have a remaining allowance of: " + currency.format(remainingAllowance)
                    : "You have a deficit of: " + currency.format(-remainingAllowance);

            // Emergency fund allocation (5% of the allowance)
            double emergencyFund = allowance * 0.05;

            // Savings goal message
            double savingsGoal = getInputValue(savingsGoalComboBox, savingsGoalTextField);
            String savingsGoalMessage;
            if (remainingAllowance >= savingsGoal) {
                savingsGoalMessage = "YEHEEEYYY! You can achieve your savings goal of " + currency.format(savingsGoal);
            } else {
                savingsGoalMessage = "You need " + currency.format(savingsGoal - remainingAllowance) + " more to achieve your savings goal.";
            }

            // Budget analysis
            String budgetAnalysis = "";
            if (totalExpenses > totalIncome) {
                budgetAnalysis = "Oh no! Your expenses exceed your income!";
            } else if (foodExpenses > (totalIncome * 0.4)) {
                budgetAnalysis = "You are spending too much on food!";
            } else {
                budgetAnalysis = "Your budget looks good!";
            }

            // Suggested savings (20% of allowance)
            double suggestedSavings = allowance * 0.2;

            // Display results
            resultArea.setText("Total Income: " + currency.format(totalIncome) + "\n");
            resultArea.append("Food Expenses: " + currency.format(foodExpenses) + "\n");
            resultArea.append("Transport Expenses: " + currency.format(transportExpenses) + "\n");
            resultArea.append("Entertainment Expenses: " + currency.format(entertainmentExpenses) + "\n");
            resultArea.append("School Expenses: " + currency.format(schoolExpenses) + "\n");
            resultArea.append("Other Expenses: " + currency.format(otherExpenses) + "\n");
            resultArea.append("Total Expenses: " + currency.format(totalExpenses) + "\n");
            resultArea.append("\n" + balanceMessage + "\n");
            resultArea.append("Emergency Fund (5% of Allowance): " + currency.format(emergencyFund) + "\n");
            resultArea.append(savingsGoalMessage + "\n");
            resultArea.append(budgetAnalysis + "\n");
            resultArea.append("Suggested Savings (20% of Allowance): " + currency.format(suggestedSavings) + "\n");
        } catch (NumberFormatException e) {
            resultArea.setText("Please enter valid numbers for all fields.");
        }
    }

    // Method to reset all inputs to their default values
    private void resetInputs() {
        allowanceComboBox.setSelectedIndex(0);
        incomeComboBox.setSelectedIndex(0);
        foodComboBox.setSelectedIndex(0);
        transportComboBox.setSelectedIndex(0);
        entertainmentComboBox.setSelectedIndex(0);
        schoolComboBox.setSelectedIndex(0);
        otherComboBox.setSelectedIndex(0);
        savingsGoalComboBox.setSelectedIndex(0);
        allowanceTextField.setText("");
        incomeTextField.setText("");
        foodTextField.setText("");
        transportTextField.setText("");
        entertainmentTextField.setText("");
        schoolTextField.setText("");
        otherTextField.setText("");
        savingsGoalTextField.setText("");
        resultArea.setText("");
    }
}
