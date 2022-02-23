package Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="books")

public class Book {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="g_id")
    @Access(AccessType.PROPERTY)
    protected Genre genre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="b_id")
    @Access(AccessType.PROPERTY)
    protected int id;

    @Column(name="b_name")
    @Access(AccessType.PROPERTY)
    protected String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_authors",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "b_id"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "a_id")
    )
    @Access(AccessType.PROPERTY)
    protected List<Author> authors = new ArrayList<>();
    public List<Author> getAuthors () {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public Book(){}
    public Book(String name){
        this.id=id;
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

    public void setGenre(Genre genre){
        this.genre=genre;
    }
    public Genre getGenre() {
        return genre;
    }
    @Override
    public String toString(){
        return "id: "+ id +", name: "+ name;
    }
}
