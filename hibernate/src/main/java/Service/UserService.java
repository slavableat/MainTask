package Service;

import Entity.Author;
import Entity.Book;
import Entity.Genre;


import java.util.List;

public class UserService {
    BookDao bookDao=new BookDao();
    GenreDao genreDao=new GenreDao();
    AuthorDao authorDao=new AuthorDao();
    public void add(Book book){
            for(Author auth :book.getAuthors())
            {
                if(!authorDao.isNewAuthor(auth)) {
                    auth.getBooks().add(book);
                    authorDao.update(auth);
                }
                else{
                auth.getBooks().add(book);
                authorDao.add(auth);
                }
            }
        if(genreDao.isNewGenre(book.getGenre())){
            book.getGenre().addBook(book);
            genreDao.add(book.getGenre());
        }
else {
            book.getGenre().addBook(book);
            genreDao.update(book.getGenre());
        }
    }
    public void update(Book book){ //uncompleted
        Book oldBook=(Book)bookDao.openTransactionSession().getSession().get(Book.class,book.getId());
        if(!book.getName().equals(oldBook.getName())) oldBook.setName(book.getName());
        Boolean mustBeDeleted=false;
                for (int i = 0; i < oldBook.getAuthors().size(); i++) {
                    Author old = oldBook.getAuthors().get(i);
                    Boolean key = true;
                    for (int k = 0; k < book.getAuthors().size(); k++) {
                        Author nEw = book.getAuthors().get(k);
                        if (nEw.getName().equals(old.getName())) {
                            if(i!=oldBook.getAuthors().size() && book.getAuthors().size()==1) {mustBeDeleted=true;}
                                else{
                                book.getAuthors().remove(nEw);
                                k--;
                                }
                            key=false;
                            break;
                        }
                    }
                    if (key) {
                        i--;
                        old.getBooks().remove(oldBook);
                        oldBook.getAuthors().remove(old);
                    }
                }
                if(!mustBeDeleted) {
                    for (Author neW : book.getAuthors()) {
                        if (!authorDao.isNewAuthor(neW)) {
                            neW.getBooks().add(book);
                            oldBook.getAuthors().add(neW);
                            authorDao.update(neW);
                        } else {
                            neW.getBooks().add(book);
                            oldBook.getAuthors().add(neW);
                            authorDao.add(neW);
                        }
                    }
                }
        bookDao.closeTransactionSesstion();
        if(!oldBook.getGenre().getName().equals(book.getGenre().getName())) {
            if(genreDao.isNewGenre(book.getGenre())){
                genreDao.add(book.getGenre());
                oldBook.setGenre((Genre)genreDao.openTransactionSession().createSQLQuery("SELECT * FROM genres WHERE g_name= :name").addEntity(Genre.class).setParameter("name",book.getGenre().getName()).getSingleResult());
                oldBook.getGenre().addBook(oldBook);
                genreDao.closeTransactionSesstion();
            }
            else{
                oldBook.setGenre(genreDao.getById(book.getGenre().getId()));
                oldBook.getGenre().addBook(oldBook);
                genreDao.update(oldBook.getGenre());
            }
        }
     }
    public void remove (int id){
        Book book=(Book)bookDao.openTransactionSession().getSession().get(Book.class,id);
        genreDao.openTransactionSession();
        Genre genre1=(Genre)genreDao.getSession().get(Genre.class,book.getGenre().getId());
        genreDao.closeTransactionSesstion();
        for (int i = 0; i <book.getAuthors().size() ; i++) {
            Author auth=book.getAuthors().get(i);
            book.getAuthors().remove(auth);
            auth.getBooks().remove(book);
            i--;
        }
        bookDao.closeTransactionSesstion();
        book=(Book)bookDao.openTransactionSession().getSession().get(Book.class,id);
        bookDao.closeTransactionSesstion();
        book.getGenre().removeBook(book);
        genreDao.update(book.getGenre());
    }
    public Book getBookById(int id){
        return bookDao.getById(id);
    }
    public List<Book> getAllBooks(){
        bookDao.openTransactionSession();
        List <Book> result=bookDao.getSession().createSQLQuery("SELECT * FROM books").addEntity(Book.class).list();
        bookDao.closeTransactionSesstion();
       return result;
    }
    public List<Author> getAllAuthors(){
        authorDao.openTransactionSession();
        List <Author> result=authorDao.getSession().createSQLQuery("SELECT * FROM authors").addEntity(Author.class).list();
        authorDao.closeTransactionSesstion();
        return result;
    }
    public List<Genre> getAllGenres(){
        genreDao.openTransactionSession();
        List <Genre> result=genreDao.getSession().createSQLQuery("SELECT * FROM genres").addEntity(Genre.class).list();
        genreDao.closeTransactionSesstion();
        return result;
    }
    public void deleteBookById(int id){
        bookDao.remove(bookDao.getById(id));
    }



}
