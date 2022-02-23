package servlets;

import Entity.Author;
import Entity.Book;
import Entity.Genre;
import Service.UserService;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private UserService userService=new UserService();
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String action = request.getServletPath();
    try {
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertBook(request, response);
                break;
            case "/delete":
                deleteBook(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;
            default:
                listBook(request, response);
                break;
        }
    } catch (SQLException ex) {
        throw new ServletException(ex);
    }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = userService.getAllBooks();
        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-book.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = userService.getBookById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("book-form.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);
    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Book newBook=new Book(request.getParameter("name"));
        Genre genre=new Genre( request.getParameter("genre"));
        String[] authors = request.getParameterValues("author");
        for (int i = 0; i < authors.length; i++) {
            if(authors[i].equals("")) continue;
            newBook.addAuthor(new Author(authors[i]));
        }
        newBook.setGenre(genre);
        userService.add(newBook);
        response.sendRedirect("list");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book=new Book(request.getParameter("name"));
        Genre genre=new Genre( request.getParameter("genre"));
        book.setGenre(genre);
        String[] authors = request.getParameterValues("author");
        for (int i = 0; i < authors.length; i++) {
            if(authors[i].equals("")) continue;
            book.addAuthor(new Author(authors[i]));
        }
        book.setId(id);
        userService.update(book);
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.remove(id);
        response.sendRedirect("list");
    }
}
