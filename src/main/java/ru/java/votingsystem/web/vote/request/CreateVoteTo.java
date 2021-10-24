package ru.java.votingsystem.web.vote.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVoteTo {
    @NotNull
    @Positive
    private Integer restaurantId;
}
