package ru.java.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Menu extends BaseEntity {

    @Column(name = "dish_name", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String dishName;

    @Column(name = "price", nullable = false)
    @NotNull
    @Min(0)
    private Integer price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "menus")
    @JsonManagedReference
    private List<Restaurant> restaurants;
}