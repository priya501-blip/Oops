import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        users.add(new User("admin", "admin123", true));
        users.add(new User("john", "1234", false));
        users.add(new User("emma", "5678", false));
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.login(username, password)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return users;
    }
}