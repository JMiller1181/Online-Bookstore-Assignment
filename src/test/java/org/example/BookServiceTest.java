package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

    @DisplayName("Tests searchBook() method")
    @Test
    void searchBookTest() {
        List<Book> bookDatabase = new ArrayList<>();
        Book book = new Book("Title", "Author", "Genre", 20);
        BookService bookService = new BookService();
        bookDatabase.add(book);
        bookService.addBook(book);
        assertEquals(bookDatabase,bookService.searchBook("Title"));
    }

    @DisplayName("Tests searchBook() method to return empty list when no matching books")
    @Test
    void searchBookNoMatchingBook() {
        List<Book> bookDatabase = new ArrayList<>();
        Book book = new Book("Title", "Author", "Genre", 20);
        BookService bookService = new BookService();
        bookService.addBook(book);
        assertEquals(bookDatabase,bookService.searchBook("Book"));
    }

    @DisplayName("Tests searchBook() method to return empty list when no books in list")
    @Test
    void searchBookNoBook() {
        List<Book> bookDatabase = new ArrayList<>();
        BookService bookService = new BookService();
        assertEquals(bookDatabase,bookService.searchBook("Book"));
    }

    @DisplayName("Tests searchBook() method to throws an exception when searching for null")
    @Test
    void searchBookNullBook() {
        Book book = new Book(null, "Author", "Genre", 20);
        BookService bookService = new BookService();
        bookService.addBook(book);
        assertThrows(NullPointerException.class,() -> bookService.searchBook(null));
    }

    @DisplayName("Tests purchaseBook() method")
    @Test
    void purchaseBookTest() {
        Book book = new Book("Title", "Author", "Genre", 20);
        User user = new User("Name", "Pass", "email");
        BookService bookService = new BookService();
        bookService.addBook(book);
        assertTrue(bookService.purchaseBook(user,book));
    }

    @DisplayName("Tests purchaseBook() method for when the book to purchase does not exist")
    @Test
    void purchaseBookNoExistingBookTest() {
        Book book = new Book("Title", "Author", "Genre", 20);
        User user = new User("Name", "Pass", "email");
        BookService bookService = new BookService();
        assertFalse(bookService.purchaseBook(user,book));
    }

    @DisplayName("Tests purchaseBook() method for when the book is null")
    @Test
    void purchaseBookNullBookTest() {
        User user = new User("Name", "Pass", "email");
        BookService bookService = new BookService();
        assertFalse(bookService.purchaseBook(user,null));
    }

    @DisplayName("Tests purchaseBook() method for when the user is null")
    @Test
    void purchaseBookNullUserTest() {
        Book book = new Book("Title", "Author", "Genre", 20);
        BookService bookService = new BookService();
        assertFalse(bookService.purchaseBook(null,book));
    }

    @DisplayName("Tests addBookReview() method")
    @Test
    void addBookReviewTest() {
        List<Book> purchasedBooks = new ArrayList<>();
        Book book = new Book("Title", "Author", "Genre", 20);
        purchasedBooks.add(book);
        User user = new User("Name", "Pass", "email", purchasedBooks);
        BookService bookService = new BookService();
        assertTrue(bookService.addBookReview(user,book,"5 stars"));
    }

    @DisplayName("Tests addBookReview() method for when the user hasn't purchased the book")
    @Test
    void addBookReviewNotPurchasedTest() {
        Book book = new Book("Title", "Author", "Genre", 20);
        User user = new User("Name", "Pass", "email");
        BookService bookService = new BookService();
        assertFalse(bookService.addBookReview(user,book,"5 stars"));
    }

    @DisplayName("Tests addBookReview() method for null user")
    @Test
    void addBookReviewNullUserTest() {
        List<Book> purchasedBooks = new ArrayList<>();
        Book book = new Book("Title", "Author", "Genre", 20);
        purchasedBooks.add(book);
        User user = new User("Name", "Pass", "email", purchasedBooks);
        BookService bookService = new BookService();
        assertThrows(NullPointerException.class, () -> bookService.addBookReview(null,book,"5 stars"));
    }

    @DisplayName("Tests addBookReview() method for null book")
    @Test
    void addBookReviewNullBookTest() {
        List<Book> purchasedBooks = new ArrayList<>();
        Book book = new Book("Title", "Author", "Genre", 20);
        purchasedBooks.add(book);
        User user = new User("Name", "Pass", "email", purchasedBooks);
        BookService bookService = new BookService();
        assertFalse(bookService.addBookReview(user,null,"5 stars"));
    }

    @DisplayName("Tests addBookReview() method for a null review")
    @Test
    void addBookReviewNullReviewTest() {
        List<Book> purchasedBooks = new ArrayList<>();
        Book book = new Book("Title", "Author", "Genre", 20);
        purchasedBooks.add(book);
        User user = new User("Name", "Pass", "email", purchasedBooks);
        BookService bookService = new BookService();
        assertTrue(bookService.addBookReview(user,book,null));
    }

    @DisplayName("Test addBook() method for adding a new book")
    @Test
    void addNewBook() {
        Book book = new Book("Title", "Author", "Genre", 20);
        BookService bookService = new BookService();
        assertTrue(bookService.addBook(book));
    }

    @DisplayName("Test addBook() method for adding an existing book")
    @Test
    void addExistingBook() {
        Book book = new Book("Title", "Author", "Genre", 20);
        BookService bookService = new BookService();
        bookService.addBook(book);
        assertFalse(bookService.addBook(book));
    }

    //I would treat this as a bug, adding a book with null values SHOULD throw an exception
    @DisplayName("Test addBook() method for adding a book with null values")
    @Test
    void addNullBook() {
        Book book = new Book(null, null,null,0);
        BookService bookService = new BookService();
        assertTrue(bookService.addBook(book));
    }

    @DisplayName("Test removeBook() method for removing a book")
    @Test
    void removeBookTest() {
        Book book = new Book("Title", "Author", "Genre", 20);
        BookService bookService = new BookService();
        bookService.addBook(book);
        assertTrue(bookService.removeBook(book));
    }
    @DisplayName("Test removeBook() method for removing a book that isn't in the database")
    @Test
    void removeBookNotExistTest() {
        Book book = new Book("Title", "Author", "Genre", 20);
        BookService bookService = new BookService();
        assertFalse(bookService.removeBook(book));
    }

    @DisplayName("Test removeBook() method for passing null")
    @Test
    void removeBookNullTest() {
        Book book = new Book("Title", "Author", "Genre", 20);
        BookService bookService = new BookService();
        bookService.addBook(book);
        assertFalse(bookService.removeBook(null));
    }


}