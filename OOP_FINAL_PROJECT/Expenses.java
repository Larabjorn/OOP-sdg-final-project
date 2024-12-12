
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
        frame = new JFrame("SpendWise Chronicles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        addInputComponent(inputPanel, "Select Weekly Allowance: ", gbc, 0, allowanceComboBox = createAmountComboBox(), allowanceTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Additional Income: ", gbc, 1, incomeComboBox = createAmountComboBox(), incomeTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Food Expenses: ", gbc, 2, foodComboBox = createExpenseComboBox(), foodTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Transport Expenses: ", gbc, 3, transportComboBox = createExpenseComboBox(), transportTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Entertainment Expenses: ", gbc, 4, entertainmentComboBox = createExpenseComboBox(), entertainmentTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select School Expenses: ", gbc, 5, schoolComboBox = createExpenseComboBox(), schoolTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Additional Expenses: ", gbc, 6, otherComboBox = createExpenseComboBox(), otherTextField = new JTextField(10));
        addInputComponent(inputPanel, "Select Savings Goal: ", gbc, 7, savingsGoalComboBox = createAmountComboBox(), savingsGoalTextField = new JTextField(10));

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setPreferredSize(new Dimension(100, 25));
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        inputPanel.add(calculateButton, gbc);

        JButton resetButton = new JButton("Reset");
        gbc.gridx = 3;
        inputPanel.add(resetButton, gbc);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        calculateButton.addActionListener(e -> calculateExpenses());

        resetButton.addActionListener(e -> resetInputs());

        frame.pack();
        frame.setVisible(true);
    }

    private void addInputComponent(JPanel panel, String labelText, GridBagConstraints gbc, int gridY, JComboBox<String> comboBox, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = gridY;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        panel.add(comboBox, gbc);

        gbc.gridx = 2;
        panel.add(textField, gbc);
        textField.setVisible(false);

        comboBox.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            textField.setVisible("Manual".equals(selected));
            panel.revalidate();
            panel.repaint();
        });
    }

    private double getInputValue(JComboBox<String> comboBox, JTextField textField) {
        String value = comboBox.getSelectedItem().toString();
        if ("Manual".equals(value)) {
            return Double.parseDouble(textField.getText().isEmpty() ? "0" : textField.getText());
        } else {
            return Double.parseDouble(value);
        }
    }

    private JComboBox<String> createAmountComboBox() {
        String[] options = new String[51];
        options[0] = "Manual";
        for (int i = 1; i <= 50; i++) {
            options[i] = String.valueOf(i * 100);
        }
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setEditable(true);
        return comboBox;
    }

    private JComboBox<String> createExpenseComboBox() {
        String[] options = new String[51];
        options[0] = "Manual";
        for (int i = 1; i <= 50; i++) {
            options[i] = String.valueOf(i * 100);
        }
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setEditable(true);
        return comboBox;
    }

    private void calculateExpenses() {
        try {
            double allowance = getInputValue(allowanceComboBox, allowanceTextField);
            double additionalIncome = getInputValue(incomeComboBox, incomeTextField);
            double totalIncome = allowance + additionalIncome;

            double foodExpenses = getInputValue(foodComboBox, foodTextField);
            double transportExpenses = getInputValue(transportComboBox, transportTextField);
            double entertainmentExpenses = getInputValue(entertainmentComboBox, entertainmentTextField);
            double schoolExpenses = getInputValue(schoolComboBox, schoolTextField);
            double otherExpenses = getInputValue(otherComboBox, otherTextField);
            double totalExpenses = foodExpenses + transportExpenses + entertainmentExpenses + schoolExpenses + otherExpenses;

            double remainingAllowance = totalIncome - totalExpenses;

            NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));
            String balanceMessage = remainingAllowance >= 0
                    ? "You have a remaining allowance of: " + currency.format(remainingAllowance)
                    : "You have a deficit of: " + currency.format(-remainingAllowance);

            double emergencyFund = allowance * 0.05;

            double savingsGoal = getInputValue(savingsGoalComboBox, savingsGoalTextField);
            String savingsGoalMessage;
            if (remainingAllowance >= savingsGoal) {
                savingsGoalMessage = "YEHEEEYYY! You can achieve your savings goal of " + currency.format(savingsGoal);
            } else {
                savingsGoalMessage = "You need " + currency.format(savingsGoal - remainingAllowance) + " more to achieve your savings goal.";
            }

            String budgetAnalysis = "";
            if (totalExpenses > totalIncome) {
                budgetAnalysis = "Oh no! Your expenses exceed your income!";
            } else if (foodExpenses > (totalIncome * 0.4)) {
                budgetAnalysis = "You are spending too much on food!";
            } else {
                budgetAnalysis = "Your budget looks good!";
            }

            double suggestedSavings = allowance * 0.2;

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
