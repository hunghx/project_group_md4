package ra.academy.dto.request;

public class FormLogin {
    private String username;
    private String password;

    public FormLogin() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FormLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
