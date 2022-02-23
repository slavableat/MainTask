package Service;


import Entity.Genre;
import dao.GenreDaoInterface;
import hibernate.SessionUtil;
import org.hibernate.SQLQuery;

import java.util.List;

public class GenreDao extends SessionUtil implements GenreDaoInterface {
    @Override
    public void add(Genre genre) {
            openTransactionSession();
            getSession().persist(genre);
            closeTransactionSesstion();
    }

    @Override
    public void remove(Genre genre) {
        openTransactionSession();
        getSession().delete(genre);
        closeTransactionSesstion();
    }

    @Override
    public void update(Genre genre) {
    openTransactionSession();
    getSession().saveOrUpdate(genre);
    closeTransactionSesstion();
    }

    @Override
    public Boolean isNewGenre(Genre genre) { //.getSingleResult()
        openTransactionSession();
        String sql="SELECT * FROM genres WHERE g_name = :name";
        SQLQuery query=getSession().createSQLQuery(sql).addEntity(Genre.class);
        query.setParameter("name",genre.getName());
        List<Genre> result=query.list();
        closeTransactionSesstion();
        if(result.isEmpty()) return true;
        genre.setBooks(result.get(0).getBooks());
        genre.setId(result.get(0).getId());
        return false;
    }

    @Override
    public Genre getById(int id) {
        openTransactionSession();
        Genre genre=getSession().get(Genre.class,id);
        closeTransactionSesstion();
        return genre;
    }

    @Override
    public List<Genre> getAll() {
        openTransactionSession();
        List<Genre> genres=getSession().createSQLQuery("SELECT * FROM genres").addEntity(Genre.class).list();
        closeTransactionSesstion();
        return genres;
    }
}
