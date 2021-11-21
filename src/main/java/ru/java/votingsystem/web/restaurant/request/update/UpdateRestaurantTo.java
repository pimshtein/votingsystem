package ru.java.votingsystem.web.restaurant.request.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRestaurantTo {
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotEmpty
    @Valid
    private List<UpdateMenuTo> menu;
}
