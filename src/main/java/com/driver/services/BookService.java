//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.services;

import com.driver.models.Author;
import com.driver.models.Book;
import com.driver.models.Genre;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository2;
    @Autowired
    AuthorRepository authorRepository1;

    public BookService() {
    }

    public void createBook(Book book) {

        int authorId = book.getAuthor().getId();

        Author author =  authorRepository1.findById(authorId).get();

        //Update the bookList written by Author
        author.getBooksWritten().add(book);

        //Updated the book
        book.setAuthor(author);
        //bookRepository2.save(book);
        bookRepository2.save(book);
        authorRepository1.save(author);
    }

    public List<Book> getBooks(Genre genre, boolean available, String author) {
        if (genre != null && author != null) {
            return this.bookRepository2.findBooksByGenreAuthor(genre, author, available);
        } else if (genre != null) {
            return this.bookRepository2.findBooksByGenre(genre, available);
        } else {
            return author != null ? this.bookRepository2.findBooksByAuthor(author, available) : this.bookRepository2.findByAvailability(available);
        }
    }
}
