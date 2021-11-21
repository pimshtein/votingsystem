package ru.java.votingsystem.web.restaurant.request.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class UpdateMenuTo {
    @NotBlank
    @Size(min = 2, max = 100)
    private String dishName;

    @NotNull
    @Positive
    @Min(0)
    private Integer price;
}
