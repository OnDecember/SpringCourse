package org.maxym.spring.controller;

import jakarta.validation.Valid;
import org.maxym.spring.model.Book;
import org.maxym.spring.model.Person;
import org.maxym.spring.service.BookService;
import org.maxym.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    @GetMapping
    public String booksPage(@RequestParam(name = "page", required = false) Integer page,
                            @RequestParam(name = "books_per_page", required = false) Integer booksPerPage,
                            @RequestParam(name = "sort_by_year", required = false) Boolean sortByYear,
                            Model model) {
        List<Book> books;

        if (nonNull(page) && nonNull(booksPerPage)) {
            if (nonNull(sortByYear) && sortByYear == Boolean.TRUE)
                books = bookService.findAll(page, booksPerPage, "year");
            else
                books = bookService.findAll(page, booksPerPage);
        } else if (nonNull(sortByYear) && sortByYear == Boolean.TRUE) {
            books = bookService.findAll("year");
        } else books = bookService.findAll();

        model.addAttribute("books", books);
        return "books/books";
    }

    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person,
                           Model model) {


        Book book = bookService.findById(id);

        if (book.getBook_id() != 0) {
            model.addAttribute("book", book);

            if (book.getPerson() != null)
                model.addAttribute("owner", book.getPerson());
            else
                model.addAttribute("people", personService.findAll());
            return "books/book";
        }
        return "redirect:/books/new";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id,
                             @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookService.update(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBookPage(@ModelAttribute("book") Book book) {
        return "/books/new";
    }

    @PostMapping
    public String createNewBook(@ModelAttribute("book") @Valid Book book,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable("id") int id,
                               Model model) {

        Book book = bookService.findById(id);
        if (book.getBook_id() != 0) {
            model.addAttribute("book", book);
            return "/books/edit";
        }
        return "redirect:/books/new";
    }

    @PatchMapping("/{id}/rent")
    public String takeBook(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person) {

        bookService.assign(id, person);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String giveBook(@PathVariable("id") int id) {

        bookService.release(id);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String searchBook(Model model,
                             @RequestParam(value = "phrase", required = false) String phrase) {
        if (nonNull(phrase))
            model.addAttribute("books", bookService.findAllByTitleStartingWith(phrase));
        return "books/search";
    }
}