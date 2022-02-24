import axios from "axios";

const page_url = "http://localhost:8080";

class BookService {
    async getBooks() {
        return await axios.get(page_url + "/books");
    }

    async getGenres() {
        return await axios.get(page_url + "/genres");
    }

    async getAuthors() {
        return await axios.get(page_url + "/authors");
    }

    async addBook(book) {
        return await axios.post(page_url + "/book-create", book);
    }

    async deleteBook(id) {
        return await axios.delete(page_url + `/delete/${id}`);
    }

    async getBookById(id) {
        return await axios.get(page_url + `/book-edit/${id}`)
    }

    async updateBook(id, book) {
        return await axios.put(page_url + `/book-edit/${id}`, book);
    }

    async getGenres() {
        return await axios.get(page_url + "/genres");
    }
}

export default new BookService;