package org.maxym.spring.repository;

import org.maxym.spring.model.Book;
import org.maxym.spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByPerson(Person person);

    List<Book> findAllByTitleStartingWith(String startWith);
}
