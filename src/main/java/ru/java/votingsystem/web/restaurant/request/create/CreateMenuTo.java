package ru.java.votingsystem.web.restaurant.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class CreateMenuTo {
    @NotBlank
    @Size(min = 2, max = 100)
    private String dishName;

    @NotNull
    @Min(0)
    private Integer price;
}
