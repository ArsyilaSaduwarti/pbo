package aplikasiukang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Kelas untuk tampilan login
public class User extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public User() {
        // Mengatur judul jendela
        setTitle("Login - PT UKANG");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 5, 5));

        // Membuat komponen UI
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Masuk");
        statusLabel = new JLabel("", SwingConstants.CENTER);

        // Menambahkan komponen ke frame
        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
        add(statusLabel);

        // Event listener untuk tombol login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                UserAuth userAuth = new UserAuth("048"); // Ganti dengan 3 digit NIM terakhir

                // Validasi login
                if (userAuth.login(username, password)) {
                    dispose(); // Menutup jendela login
                    new HitungSiku(); // Buka frame hitung siku
                } else {
                    statusLabel.setText("Login gagal! Coba lagi.");
                    statusLabel.setForeground(Color.RED);
                }
            }
        });

        setVisible(true);
    }
}
