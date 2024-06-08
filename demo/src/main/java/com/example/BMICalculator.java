package com.example;

import com.example.database.DatabaseConnection;
import com.example.model.BMIRecord;
import com.example.model.dao.BMIRecordDAO;
import com.example.exception.DataAccessException;
import com.example.exception.InvalidInputException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculator extends JFrame {
    private JTextField heightField;
    private JTextField weightField;
    private JLabel resultLabel;
    private BMIRecordDAO bmiRecordDAO;

    public BMICalculator() {
        bmiRecordDAO = new BMIRecordDAO(DatabaseConnection.getConnection());
        setTitle("Calculadora de IMC");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Altura (m):"));
        heightField = new JTextField();
        add(heightField);

        add(new JLabel("Peso (kg):"));
        weightField = new JTextField();
        add(weightField);

        JButton calculateButton = new JButton("Calcular");
        calculateButton.addActionListener(new CalculateButtonListener());
        add(calculateButton);

        resultLabel = new JLabel("Resultado: ");
        add(resultLabel);

        JButton viewRecordsButton = new JButton("Ver Registros");
        viewRecordsButton.addActionListener(new ViewRecordsButtonListener());
        add(viewRecordsButton);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());

                if (height <= 0 || weight <= 0) {
                    throw new InvalidInputException("Altura e peso devem ser positivos.");
                }

                double bmi = weight / (height * height);
                resultLabel.setText(String.format("Resultado: %.2f", bmi));

                BMIRecord record = new BMIRecord(height, weight, bmi);
                bmiRecordDAO.save(record);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(BMICalculator.this, "Por favor, insira números válidos.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidInputException ex) {
                JOptionPane.showMessageDialog(BMICalculator.this, ex.getMessage(), "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(BMICalculator.this, "Erro ao acessar o banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ViewRecordsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            java.util.List<BMIRecord> records;
            try {
                records = bmiRecordDAO.getAllRecords();
                StringBuilder recordsText = new StringBuilder("Registros de IMC:\n");
                for (BMIRecord record : records) {
                    recordsText.append(record).append("\n");
                    }
                JOptionPane.showMessageDialog(BMICalculator.this, recordsText.toString(), "Registros de IMC", JOptionPane.INFORMATION_MESSAGE);
            } catch (DataAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BMICalculator calculator = new BMICalculator();
            calculator.setVisible(true);
        });
    }
}
