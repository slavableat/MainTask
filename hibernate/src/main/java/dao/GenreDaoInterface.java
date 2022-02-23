package dao;

import Entity.Genre;

import java.util.List;

public interface GenreDaoInterface {
    void add(Genre genre);
    void remove(Genre genre);
    void update(Genre genre);
    Boolean isNewGenre(Genre genre);
    Genre getById(int id);
    List<Genre> getAll();

}
