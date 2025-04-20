import java.util.Scanner;

public class BankApp {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- BANK MANAGEMENT SYSTEM ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choice: ");
            int choice = getInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Thank you for using the system.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.authenticate(username, password);
        if (user != null) {
            if (user.isAdmin()) {
                adminMenu();
            } else {
                customerMenu(user);
            }
        } else {
            System.out.println("Login failed. Try again.");
        }
    }

    private static void customerMenu(User user) {
        while (true) {
            System.out.println("\n--- CUSTOMER MENU ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            int choice = getInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: $" + user.getAccount().getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount: ");
                    double deposit = getDouble();
                    user.getAccount().deposit(deposit);
                    break;
                case 3:
                    System.out.print("Enter amount: ");
                    double withdraw = getDouble();
                    user.getAccount().withdraw(withdraw);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View All Customers");
            System.out.println("2. Logout");
            System.out.print("Choice: ");
            int choice = getInt();

            switch (choice) {
                case 1:
                    for (User user : userService.getAllUsers()) {
                        if (!user.isAdmin()) {
                            System.out.println("User: " + user.getUsername() + " | Balance: $" + user.getAccount().getBalance());
                        }
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static int getInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline11
        return value;
    }

    private static double getDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Enter a valid amount: ");
            scanner.next();
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return value;
    }
}
