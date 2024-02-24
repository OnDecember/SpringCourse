package org.maxym.spring.springbootproject2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "book", schema = "public")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int book_id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 255, message = "Title should be between 2 and 255 symbols")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 255, message = "Author name should be between 2 and 255 symbols")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Year should be greater then 0")
    @Max(value = 2024, message = "Year should be less then 2024")
    @Column(name = "year")
    private int year;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "assign_date")
    private Date assignDate;

    @Transient
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person person;
}
