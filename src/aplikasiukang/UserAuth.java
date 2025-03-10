package aplikasiukang;

// Kelas untuk menangani autentikasi pengguna
public class UserAuth {
    private String username;
    private String password;

    // Konstruktor untuk menginisialisasi username dan password
    public UserAuth(String nimSuffix) {
        this.username = "arsyila";
        this.password = "12345";
    }

    // Metode untuk melakukan login
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
}
