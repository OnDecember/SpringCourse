package org.maxym.spring.springbootproject2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person", schema = "public")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 255, message = "Name should be between 2 and 255 symbols")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 1900, message = "Year should be greater then 1900")
    @Max(value = 2024, message = "Year should be less then 2024")
    @Column(name = "birth_year")
    private int birthYear;

    @OneToMany(mappedBy = "person")
    private List<Book> books;
}
