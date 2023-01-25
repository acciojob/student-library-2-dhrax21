//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.controller;

import com.driver.models.Book;
import com.driver.models.Genre;
import com.driver.services.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/book"})
public class BookController {
    @Autowired
    BookService bookService;

    public BookController() {
    }

    @PostMapping({"/"})
    public ResponseEntity createBook(@RequestBody Book book) {
        this.bookService.createBook(book);
        return new ResponseEntity("the book is added successfully", HttpStatus.CREATED);
    }

    @GetMapping({"/"})
    public ResponseEntity getBooks(@RequestParam(value = "genre",required = false) Genre genre, @RequestParam(value = "available",required = false,defaultValue = "false") boolean available, @RequestParam(value = "author",required = false) String author) {
        List<Book> bookList = this.bookService.getBooks(genre, available, author);
        return new ResponseEntity(bookList, HttpStatus.OK);
    }
}
