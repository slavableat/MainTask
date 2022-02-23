package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="authors")
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    @Column (name="a_id")
    protected int id;
    @Column (name="a_name")
    @Access(AccessType.PROPERTY)
    protected String name;
    @ManyToMany(mappedBy = "authors",fetch=FetchType.EAGER)
    @Access(AccessType.PROPERTY)
    protected List<Book> books = new ArrayList<>();
    public List<Book> getBooks(){
        return books;
    }
    public void setBooks(List<Book> books){
        this.books=books;
    }

    public Author(){}
    public Author(String name){
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

    @Override
    public String toString(){
        return "id: "+ id +", name: "+ name;
    }
}

