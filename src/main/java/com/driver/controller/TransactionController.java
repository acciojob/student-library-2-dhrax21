//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.controller;

import com.driver.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/transaction"})
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    public TransactionController() {
    }

    public ResponseEntity issueBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception {
        this.transactionService.issueBook(cardId, bookId);
        return new ResponseEntity("transaction completed", HttpStatus.ACCEPTED);
    }

    public ResponseEntity returnBook(@RequestParam("cardId") int cardId, @RequestParam("bookId") int bookId) throws Exception {
        this.transactionService.returnBook(cardId, bookId);
        return new ResponseEntity("transaction completed", HttpStatus.ACCEPTED);
    }
}
