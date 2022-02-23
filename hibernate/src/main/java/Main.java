
import Entity.Book;
import Service.UserService;
import hibernate.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService=new UserService();
        List<Book> arr=userService.getAllBooks();
        HibernateUtil.shutdown();
    }
}
