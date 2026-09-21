import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageOperation {

    public static void operate(int key) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image File");

        // Optional: Filter only image files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "bmp");
        fileChooser.setFileFilter(filter);

        int choice = fileChooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                // Reading image bytes
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);

                // Applying XOR encryption/decryption
                for (int i = 0; i < data.length; i++) {
                    data[i] = (byte) (data[i] ^ key);
                }

                // Writing modified bytes back to the file
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(data);

                fos.close();
                fis.close();

                JOptionPane.showMessageDialog(null, "Success! Operation completed on:\n" + file.getName(),
                        "Done", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        // Set Look and Feel to native system theme for a better UI appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        JFrame frame = new JFrame("Image Encryptor & Decryptor");
        frame.setSize(450, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // Title Label
        JLabel titleLabel = new JLabel("Secure Image Tool");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        frame.add(titleLabel, gbc);

        // Key Label & Input
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        JLabel keyLabel = new JLabel("Enter Secret Key (Number):");
        keyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        frame.add(keyLabel, gbc);

        gbc.gridx = 1;
        JTextField textField = new JTextField(10);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        frame.add(textField, gbc);

        // Action Button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton button = new JButton("Select Image & Process");
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(60, 130, 246));
        button.setForeground(Color.WHITE);
        frame.add(button, gbc);

        // Button Click Event Handler
        button.addActionListener(e -> {
            try {
                String text = textField.getText().trim();
                if (text.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid integer key!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int key = Integer.parseInt(text);
                operate(key);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Key must be a valid integer number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}
