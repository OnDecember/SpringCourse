package org.maxym.spring.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {

    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 255, message = "Name should be between 2 and 255 symbols")
    private String fullName;

    @Min(value = 1900, message = "Year should be greater then 1900")
    @Max(value = 2024, message = "Year should be less then 2024")
    private int birthYear;
}
