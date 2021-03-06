package ru.java.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "menus",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"restaurant_id", "dish_name"})
)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"restaurant"})
public class Menu extends BaseEntity {

    @Column(name = "dish_name", nullable = false, unique = true)
    @NotBlank
    @Size(max = 100)
    private String dishName;

    @Column(name = "price", nullable = false)
    @NotNull
    @Min(0)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NotNull
    private Restaurant restaurant;

    public Menu(String dishName, Integer price) {
        this.dishName = dishName;
        this.price = price;
    }

    public Menu(Integer id, String dishName, Integer price) {
        super.id = id;
        this.dishName = dishName;
        this.price = price;
    }
}