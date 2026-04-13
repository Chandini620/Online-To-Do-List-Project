
import java.sql.*;
import java.util.Scanner;

public class TodoApp {

    static final String URL = "jdbc:mysql://localhost:3306/todo_db";
    static final String USER = "root";
    static final String PASSWORD = "password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            while (true) {
                System.out.println("\n1. Add Task");
                System.out.println("2. View Tasks");
                System.out.println("3. Mark Task Completed");
                System.out.println("4. Delete Task");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();

                if (choice == 1) {
                    sc.nextLine();
                    System.out.print("Enter task: ");
                    String task = sc.nextLine();

                    String query = "INSERT INTO tasks(task, status) VALUES (?, 'pending')";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, task);
                    ps.executeUpdate();

                    System.out.println("Task added!");

                } else if (choice == 2) {
                    String query = "SELECT * FROM tasks";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        System.out.println(
                            rs.getInt("id") + " | " +
                            rs.getString("task") + " | " +
                            rs.getString("status")
                        );
                    }

                } else if (choice == 3) {
                    System.out.print("Enter task ID: ");
                    int id = sc.nextInt();

                    String query = "UPDATE tasks SET status='completed' WHERE id=?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, id);
                    ps.executeUpdate();

                    System.out.println("Task marked as completed!");

                } else if (choice == 4) {
                    System.out.print("Enter task ID: ");
                    int id = sc.nextInt();

                    String query = "DELETE FROM tasks WHERE id=?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, id);
                    ps.executeUpdate();

                    System.out.println("Task deleted!");

                } else {
                    break;
                }
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
