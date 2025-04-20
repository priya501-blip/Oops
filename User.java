public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private Account account;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        if (!isAdmin) {
            this.account = new Account();
        }
    }

    public boolean login(String uname, String pwd) {
        return this.username.equals(uname) && this.password.equals(pwd);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public Account getAccount() {
        return account;
    }
}