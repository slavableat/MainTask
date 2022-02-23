package Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="genres")

public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    @Column(name="g_id")
    protected int id;
    @Column(name="g_name" )
    @Access(AccessType.PROPERTY)
    protected String name;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "genre"  ,orphanRemoval = true, cascade = CascadeType.ALL) //или DETACH
    protected List<Book> books=new ArrayList<Book>();

    public Genre(){}
    public Genre(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void removeBook(Book book){
        books.remove(book);
    }

    public void setBooks(List<Book> books){
        this.books=books;
    }
    public List<Book> getBooks(){
        return books;
    }

    @Override
    public String toString(){
        return "id: "+ id +", name: "+ name;
    }
}
