package org.maxym.spring.controllers;

import jakarta.validation.Valid;
import org.maxym.spring.dao.BookDAO;
import org.maxym.spring.dao.PersonDAO;
import org.maxym.spring.models.Book;
import org.maxym.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String booksPage(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int id,
                           Model model) {

        Optional<Book> optional = bookDAO.getBook(id);
        if (optional.isPresent()) {
            Book book = optional.get();
            Person person = book.getPersonId() == null ? new Person() : personDAO.getPerson(book.getPersonId()).get();
            model.addAttribute("book", book);
            model.addAttribute("person", person);
            model.addAttribute("people", personDAO.getPeople());
            return "/books/book";
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

        bookDAO.update(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
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

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable("id") int id,
                               Model model) {

        Optional<Book> optional = bookDAO.getBook(id);
        if (optional.isPresent()) {
            model.addAttribute("book", optional.get());
            return "/books/edit";
        }
        return "redirect:/books/new";
    }

    @PatchMapping("/{id}/rent")
    public String takeBook(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person) {

        bookDAO.updatePerson(id, person.getPerson_id());
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String giveBook(@PathVariable("id") int id) {

        bookDAO.updatePerson(id, null);
        return "redirect:/books";
    }
}