package org.maxym.spring.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {

    private int book_id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 255, message = "Title should be between 2 and 255 symbols")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 255, message = "Author name should be between 2 and 255 symbols")
    private String author;

    @Min(value = 0, message = "Year should be greater then 0")
    @Max(value = 2024, message = "Year should be less then 2024")
    private int year;

    private Integer personId;
}
