package dao;

import Entity.Book;

import java.util.List;

public interface BookDaoInterface {
    void add(Book book);
    void remove(Book book);
    void update(Book book);
    Book getById(int id);
    List<Book> getAll();

}
