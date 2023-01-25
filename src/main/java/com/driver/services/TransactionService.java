//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.driver.services;

import com.driver.models.Book;
import com.driver.models.Card;
import com.driver.models.CardStatus;
import com.driver.models.Transaction;
import com.driver.models.TransactionStatus;
import com.driver.repositories.BookRepository;
import com.driver.repositories.CardRepository;
import com.driver.repositories.TransactionRepository;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    BookRepository bookRepository5;
    @Autowired
    CardRepository cardRepository5;
    @Autowired
    TransactionRepository transactionRepository5;
    @Value("${books.max_allowed}")
    public int max_allowed_books;
    @Value("${books.max_allowed_days}")
    public int getMax_allowed_days;
    @Value("${books.fine.per_day}")
    public int fine_per_day;

    public TransactionService() {
    }

    public String issueBook(int cardId, int bookId) throws Exception {
        Book book = (Book)this.bookRepository5.findById(bookId).get();
        Card card = (Card)this.cardRepository5.findById(cardId).get();
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setIssueOperation(true);
        if (book != null && book.isAvailable()) {
            if (card != null && !card.getCardStatus().equals(CardStatus.DEACTIVATED)) {
                if (card.getBooks().size() >= this.max_allowed_books) {
                    transaction.setTransactionStatus(TransactionStatus.FAILED);
                    this.bookRepository5.updateBook(book);
                    this.transactionRepository5.save(transaction);
                    throw new Exception("Book limit has reached for this card");
                } else {
                    book.setCard(card);
                    book.setAvailable(false);
                    List<Book> bookList = card.getBooks();
                    bookList.add(book);
                    card.setBooks(bookList);
                    this.bookRepository5.updateBook(book);
                    transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                    this.transactionRepository5.save(transaction);
                    return transaction.getTransactionId();
                }
            } else {
                transaction.setTransactionStatus(TransactionStatus.FAILED);
                this.bookRepository5.updateBook(book);
                this.transactionRepository5.save(transaction);
                throw new Exception("Card is invalid");
            }
        } else {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            this.bookRepository5.updateBook(book);
            this.transactionRepository5.save(transaction);
            throw new Exception("Book is either unavailable or not present");
        }
    }

    public Transaction returnBook(int cardId, int bookId) throws Exception {
        List<Transaction> transactions = this.transactionRepository5.find(cardId, bookId, TransactionStatus.SUCCESSFUL, true);
        Transaction transaction = (Transaction)transactions.get(transactions.size() - 1);
        Date issueDate = transaction.getTransactionDate();
        long timeIssuetime = Math.abs(System.currentTimeMillis() - issueDate.getTime());
        long no_of_days_passed = TimeUnit.DAYS.convert(timeIssuetime, TimeUnit.MILLISECONDS);
        int fine = 0;
        if (no_of_days_passed > (long)this.getMax_allowed_days) {
            fine = (int)((no_of_days_passed - (long)this.getMax_allowed_days) * (long)this.fine_per_day);
        }

        Book book = transaction.getBook();
        book.setAvailable(true);
        book.setCard((Card)null);
        this.bookRepository5.updateBook(book);
        Transaction tr = new Transaction();
        tr.setBook(transaction.getBook());
        tr.setCard(transaction.getCard());
        tr.setIssueOperation(false);
        tr.setFineAmount(fine);
        tr.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        this.transactionRepository5.save(tr);
        return tr;
    }
}
