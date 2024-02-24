package org.maxym.spring.springbootproject2.repository;

import org.maxym.spring.springbootproject2.model.Book;
import org.maxym.spring.springbootproject2.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByPerson(Person person);

    List<Book> findAllByTitleStartingWith(String startWith);
}
