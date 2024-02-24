package org.maxym.spring.springbootproject2.service;

import org.maxym.spring.springbootproject2.model.Book;
import org.maxym.spring.springbootproject2.model.Person;
import org.maxym.spring.springbootproject2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAll(int page, int booksPerPage) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> findAll(String field) {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public List<Book> findAll(int page, int booksPerPage, String field) {
        return bookRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by(field))).getContent();
    }

    public List<Book> findAllByTitleStartingWith(String startWith) {
        return bookRepository.findAllByTitleStartingWith(startWith);
    }

    public List<Book> findAllByPerson(Person person) {
        LocalDate localDate = LocalDate.now().minusDays(10);
        List<Book> books = bookRepository.findAllByPerson(person);

        books.stream()
                .filter(book -> nonNull(book.getAssignDate()))
                .filter(book -> book.getAssignDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(localDate))
                .forEach(book -> book.setExpired(true));

        return books;
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(new Book());
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(Book book, int id) {
        book.setBook_id(id);
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, Person person) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            Book book = optional.get();
            book.setPerson(person);
            book.setAssignDate(new Date());
        }
    }

    @Transactional
    public void release(int id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            Book book = optional.get();
            book.setPerson(null);
            book.setAssignDate(null);
        }
    }
}
