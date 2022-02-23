package dao;

import Entity.Author;


import java.util.List;

public interface AuthorDaoInterface {
    void add(Author author);
    void remove(Author author);
    void update(Author author);
    Author getById(int id);
    List<Author> getAll();
}
