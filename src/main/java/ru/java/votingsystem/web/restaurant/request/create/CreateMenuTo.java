package ru.java.votingsystem.web.restaurant.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class CreateMenuTo {
    @NotBlank
    @Size(min = 2, max = 100)
    private String dishName;

    @NotNull
    @Positive
    private Integer price;
}
