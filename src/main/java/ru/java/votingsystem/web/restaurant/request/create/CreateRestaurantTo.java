package ru.java.votingsystem.web.restaurant.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateRestaurantTo {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotEmpty
    private List<CreateMenuTo> menu;
}
