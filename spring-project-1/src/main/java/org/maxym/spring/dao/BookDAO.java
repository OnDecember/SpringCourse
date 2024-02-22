package org.maxym.spring.dao;

import org.maxym.spring.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[] {id}, new BeanPropertyRowMapper<>(Book.class));
    }



    public Optional<Book> getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream()
                .findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year, person_id) VALUES (?, ?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear(), book.getPersonId());
    }

    public void update(Book book, int id) {
        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year = ? WHERE book_id = ?", book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id = ?", id);
    }

    public void updatePerson(int id, Integer personId) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE book_id = ?", personId, id);
    }
}
