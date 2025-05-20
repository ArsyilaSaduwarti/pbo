package view;

import controller.MovieController;
import model.Movie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MovieView extends JFrame {
    private MovieController controller;

    private JTable movieTable;
    private DefaultTableModel tableModel;

    private JTextField txtJudul;
    private JTextField txtAlur;
    private JTextField txtPenokohan;
    private JTextField txtAkting;

    private JButton btnTambah;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;

    private int selectedMovieId = -1; 

    public MovieView() {
        controller = new MovieController();
        setTitle("Movie Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadMoviesToTable();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5)); 

        String[] columnNames = {"ID", "Judul", "Alur", "Penokohan", "Akting", "Rating"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        movieTable = new JTable(tableModel);
        movieTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(movieTable);
        leftPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(leftPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 10)); 

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Judul"), gbc);
        gbc.gridx = 1;
        txtJudul = new JTextField(20);
        formPanel.add(txtJudul, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Alur"), gbc);
        gbc.gridx = 1;
        txtAlur = new JTextField(20);
        formPanel.add(txtAlur, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Penokohan"), gbc);
        gbc.gridx = 1;
        txtPenokohan = new JTextField(20);
        formPanel.add(txtPenokohan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Akting"), gbc);
        gbc.gridx = 1;
        txtAkting = new JTextField(20);
        formPanel.add(txtAkting, gbc);

        rightPanel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        buttonPanel.add(btnTambah);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(rightPanel, BorderLayout.EAST);

        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMovie();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMovie();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMovie();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        movieTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = movieTable.getSelectedRow();
                if (selectedRow != -1) {
                    selectedMovieId = (int) tableModel.getValueAt(selectedRow, 0);
                    txtJudul.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtAlur.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtPenokohan.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    txtAkting.setText(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }
        });
    }

    private void loadMoviesToTable() {
        tableModel.setRowCount(0); 
        List<Movie> movies = controller.getAllMovies();
        if (movies != null) {
            for (Movie movie : movies) {
                Object[] rowData = {
                        movie.getId(),
                        movie.getJudul(),
                        movie.getAlur(),
                        movie.getPenokohan(),
                        movie.getAkting(),
                        String.format("%.2f", movie.getRating()) 
                };
                tableModel.addRow(rowData);
            }
        }
    }

    private void addMovie() {
        String judul = txtJudul.getText();
        double alur, penokohan, akting;

        try {
            alur = Double.parseDouble(txtAlur.getText());
            penokohan = Double.parseDouble(txtPenokohan.getText());
            akting = Double.parseDouble(txtAkting.getText());

            if (judul.isEmpty() || alur < 0 || alur > 5 || penokohan < 0 || penokohan > 5 || akting < 0 || akting > 5) {
                JOptionPane.showMessageDialog(this, "Harap isi semua kolom dengan nilai valid (0-5 untuk rating).", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Movie movie = new Movie(judul, alur, penokohan, akting);
            controller.addMovie(movie); 
            loadMoviesToTable();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai Alur, Penokohan, Akting harus angka.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMovie() {
        if (selectedMovieId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih movie dari tabel untuk diupdate.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String judul = txtJudul.getText();
        double alur, penokohan, akting;

        try {
            alur = Double.parseDouble(txtAlur.getText());
            penokohan = Double.parseDouble(txtPenokohan.getText());
            akting = Double.parseDouble(txtAkting.getText());

            if (judul.isEmpty() || alur < 0 || alur > 5 || penokohan < 0 || penokohan > 5 || akting < 0 || akting > 5) {
                JOptionPane.showMessageDialog(this, "Harap isi semua kolom dengan nilai valid (0-5 untuk rating).", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Movie movie = new Movie(selectedMovieId, judul, alur, penokohan, akting);
            controller.updateMovie(movie); 
            loadMoviesToTable();
            clearForm();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai Alur, Penokohan, Akting harus angka.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteMovie() {
        if (selectedMovieId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih movie dari tabel untuk dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus movie ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteMovie(selectedMovieId); 
            loadMoviesToTable();
            clearForm();
        }
    }

    private void clearForm() {
        txtJudul.setText("");
        txtAlur.setText("");
        txtPenokohan.setText("");
        txtAkting.setText("");
        selectedMovieId = -1; 
        movieTable.clearSelection(); 
    }
}