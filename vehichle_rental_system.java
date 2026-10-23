
import javax.swing.*;
import java.awt.*;

public class VehicleRentalSystem extends JFrame {

    JTextField customerField, daysField;
    JComboBox<String> vehicleBox, paymentBox;
    JTextArea receiptArea;
    JLabel amountLabel;

    public VehicleRentalSystem() {
        setTitle("Vehicle Rental Booking System");
        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Vehicle Rental Booking");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading, BorderLayout.NORTH);

        customerField = new JTextField();

        vehicleBox = new JComboBox<>(new String[]{
            "Bike - 500",
            "Car - 2000",
            "SUV - 3500",
            "Van - 4000"
        });

        daysField = new JTextField();

        paymentBox = new JComboBox<>(new String[]{
            "Cash", "UPI", "Credit/Debit Card"
        });

        amountLabel = new JLabel("Rental Amount: Rs. 0");

        JButton calculateButton = new JButton("Calculate Amount");
        JButton bookButton = new JButton("Book Vehicle");
        JButton clearButton = new JButton("Clear");

        panel.add(new JLabel("Customer Name:"));
        panel.add(customerField);

        panel.add(new JLabel("Select Vehicle:"));
        panel.add(vehicleBox);

        panel.add(new JLabel("Rental Days:"));
        panel.add(daysField);

        panel.add(new JLabel("Payment Method:"));
        panel.add(paymentBox);

        panel.add(calculateButton);
        panel.add(amountLabel);

        panel.add(bookButton);
        panel.add(clearButton);

        receiptArea = new JTextArea(8, 30);
        receiptArea.setEditable(false);

        add(panel, BorderLayout.CENTER);
        add(new JScrollPane(receiptArea), BorderLayout.SOUTH);

        calculateButton.addActionListener(e -> calculateAmount());
        bookButton.addActionListener(e -> bookVehicle());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private int getDailyRate() {
        String vehicle = (String) vehicleBox.getSelectedItem();
        return Integer.parseInt(
            vehicle.substring(vehicle.lastIndexOf("-") + 1).trim()
        );
    }

    private void calculateAmount() {
        try {
            int days = Integer.parseInt(daysField.getText().trim());

            if (days <= 0) {
                JOptionPane.showMessageDialog(
                    this, "Enter a valid number of days."
                );
                return;
            }

            int total = getDailyRate() * days;
            amountLabel.setText("Rental Amount: Rs. " + total);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Please enter rental days as a number."
            );
        }
    }

    private void bookVehicle() {
        String customer = customerField.getText().trim();

        if (customer.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please enter customer name."
            );
            return;
        }

        try {
            int days = Integer.parseInt(daysField.getText().trim());

            if (days <= 0) {
                JOptionPane.showMessageDialog(
                    this, "Rental days must be greater than zero."
                );
                return;
            }

            String vehicle = (String) vehicleBox.getSelectedItem();
            String payment = (String) paymentBox.getSelectedItem();
            int total = getDailyRate() * days;

            receiptArea.setText(
                "====== BOOKING RECEIPT ======\n\n" +
                "Customer Name: " + customer + "\n" +
                "Vehicle: " + vehicle.substring(
                    0, vehicle.lastIndexOf("-")).trim() + "\n" +
                "Rental Days: " + days + "\n" +
                "Daily Rate: Rs. " + getDailyRate() + "\n" +
                "Total Amount: Rs. " + total + "\n" +
                "Payment Method: " + payment + "\n" +
                "Payment Status: Selected\n" +
                "Booking Status: Confirmed\n\n" +
                "Thank you for choosing us!"
            );

            JOptionPane.showMessageDialog(
                this, "Vehicle booking confirmed!"
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Please enter valid rental days."
            );
        }
    }

    private void clearFields() {
        customerField.setText("");
        daysField.setText("");
        vehicleBox.setSelectedIndex(0);
        paymentBox.setSelectedIndex(0);
        amountLabel.setText("Rental Amount: Rs. 0");
        receiptArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VehicleRentalSystem::new);
    }
}
