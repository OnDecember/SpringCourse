//package org.maxym.spring.dao;
//
//import org.maxym.spring.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PersonDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> getPeople() {
//        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Optional<Person> getPerson(int id) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream()
//                .findAny();
//    }
//
//    public Optional<Person> getPerson(String name) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE full_name = ?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
//                .stream()
//                .findAny();
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO person(full_name, birth_year) VALUES (?, ?)", person.getFullName(), person.getBirthYear());
//    }
//
//    public void update(Person person, int id) {
//        jdbcTemplate.update("UPDATE person SET full_name = ?, birth_year = ? WHERE person_id = ?", person.getFullName(), person.getBirthYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM person WHERE person_id = ?", id);
//    }
//}