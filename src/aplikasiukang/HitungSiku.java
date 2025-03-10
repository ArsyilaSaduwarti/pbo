package aplikasiukang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Kelas untuk tampilan perhitungan segitiga siku-siku
public class HitungSiku extends JFrame {
    private JTextField baseField;
    private JTextField heightField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public HitungSiku() {
        // Mengatur judul jendela
        setTitle("Hitung Sisi Miring - PT UKANG");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 5, 5));

        // Membuat komponen UI
        JLabel baseLabel = new JLabel("Alas:");
        baseField = new JTextField();
        JLabel heightLabel = new JLabel("Tinggi:");
        heightField = new JTextField();
        calculateButton = new JButton("Hitung");
        resultLabel = new JLabel("", SwingConstants.CENTER);

        // Menambahkan komponen ke frame
        add(baseLabel);
        add(baseField);
        add(heightLabel);
        add(heightField);
        add(new JLabel());
        add(calculateButton);
        add(resultLabel);

        // Event listener untuk tombol hitung
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Mengambil input dari pengguna
                    double base = Double.parseDouble(baseField.getText());
                    double height = Double.parseDouble(heightField.getText());

                    // Menghitung sisi miring segitiga siku-siku
                    double hypotenuse = Math.sqrt(Math.pow(base, 2) + Math.pow(height, 2));

                    // Menampilkan hasil dengan presisi 5 angka di belakang koma
                    resultLabel.setText("Sisi miring: " + String.format("%.5f", hypotenuse));
                    resultLabel.setForeground(Color.BLUE);
                } catch (NumberFormatException ex) {
                    // Menangani kesalahan jika input tidak valid
                    resultLabel.setText("Input tidak valid!");
                    resultLabel.setForeground(Color.RED);
                } catch (Exception ex) {
                    // Menangani kesalahan umum lainnya
                    resultLabel.setText("Terjadi kesalahan!");
                    resultLabel.setForeground(Color.RED);
                }
            }
        });

        setVisible(true);
    }
}
