package Service;

import Entity.Book;
import dao.BookDaoInterface;
import hibernate.SessionUtil;


import java.util.List;

public class BookDao extends SessionUtil implements BookDaoInterface{

    @Override
    public void add(Book book) {
    openTransactionSession();
    getSession().persist(book);
    closeTransactionSesstion();
    }

    @Override
    public void remove(Book book) {
    openTransactionSession();
    getSession().delete(book);
    closeTransactionSesstion();
    }

    @Override
    public void update(Book book) {
    openTransactionSession();
    getSession().update(book);
    closeTransactionSesstion();
    }

    @Override
    public Book getById(int id) {
        openTransactionSession();
        Book book= getSession().get(Book.class,id);
        closeTransactionSesstion();
        return book;
    }

    @Override
    public List<Book> getAll() {
        openTransactionSession();
        List<Book> books=getSession().createSQLQuery("SELECT * FROM books").addEntity(Book.class).list();
        closeTransactionSesstion();
        return books;
    }
}

