import java.sql.*;
import java.util.Scanner;
public class Main {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library";
    static final String USER = "slava";
    static final String PASSWORD = "root";
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        String SQL = null;
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        while(true) {
            System.out.println("Choise:\n1)Add element\n2)Delete element\n3)Show table\n4)Edit element\n5)Exit");
            int choice;
            Scanner scan = new Scanner(System.in);
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("Enter new field: ");
                    String buf = scan.nextLine();
                    SQL = "insert into books  set b_name =?";
                    preparedStatement = connection.prepareStatement(SQL);
                    preparedStatement.setString(1, buf);
                    preparedStatement.executeUpdate();
                    break;
                }
                case 2: {
                    SQL = "delete  from books where b_name=?";
                    System.out.println("What book must be deleted?");
                    String buf = scan.nextLine();
                    preparedStatement = connection.prepareStatement(SQL);
                    preparedStatement.setString(1, buf);
                    preparedStatement.executeUpdate();
                    break;
                }
                case 3: {
                    SQL = "select * from books";
                    statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(SQL);
                    while (resultSet.next()) {
                        System.out.println("id: " + resultSet.getInt("b_id"));
                        System.out.println("name: " + resultSet.getString("b_name"));
                        System.out.println("\n===================\n");
                    }
                    break;
                }
                case 4:{
                    SQL="UPDATE books SET b_name = ?  WHERE b_name = ?";
                    System.out.println("What record must be changed? Enter name:");
                    String buf=scan.nextLine();
                    preparedStatement=connection.prepareStatement(SQL);
                    preparedStatement.setString(2,buf);
                    buf=scan.nextLine();
                    preparedStatement.setString(1,buf);
                    preparedStatement.executeUpdate();
                }
                case 5:{
                    connection.close();
                    return;
                }
                default:
                    System.out.println("Incorrect input!");
            }
        }
    }
}
