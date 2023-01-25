//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.controller;

import com.driver.models.Author;
import com.driver.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/author"})
public class AuthorController {
    @Autowired
    AuthorService authorService;

    public AuthorController() {
    }

    @PostMapping({"/"})
    public ResponseEntity<String> createAuthor(@RequestBody Author author) {
        this.authorService.create(author);
        return new ResponseEntity("the author added successfully", HttpStatus.CREATED);
    }
}
