package Service;

import Entity.Author;
import dao.AuthorDaoInterface;

import hibernate.SessionUtil;
import org.hibernate.SQLQuery;

import java.util.List;

public class AuthorDao extends SessionUtil implements AuthorDaoInterface {

    @Override
    public void add(Author author) {
        openTransactionSession();
        getSession().persist(author);
        closeTransactionSesstion();
    }

    @Override
    public void remove(Author author) {
        openTransactionSession();
        getSession().remove(author);
        closeTransactionSesstion();
    }

    @Override
    public void update(Author author) {
        openTransactionSession();
        getSession().update(author);
        closeTransactionSesstion();
    }

    @Override
    public Author getById(int id) {
        openTransactionSession();
        Author author=getSession().get(Author.class,id);
        return author;
    }

    @Override
    public List<Author> getAll() {
        openTransactionSession();
        List<Author> result=getSession().createSQLQuery("SELECT * FROM authors").addEntity(Author.class).list();
        return result;
    }
    public Boolean isNewAuthor(Author author) { //.getSingleResult()
        openTransactionSession();
        String sql="SELECT * FROM authors WHERE a_name = :name";
        SQLQuery query=getSession().createSQLQuery(sql).addEntity(Author.class);
        query.setParameter("name",author.getName());
        List<Author> result=query.list();
        closeTransactionSesstion();
        if(result.isEmpty()) return true;
        author.setBooks(result.get(0).getBooks());
        author.setId(result.get(0).getId());
        return false;
    }
}
