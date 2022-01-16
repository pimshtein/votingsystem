package ru.java.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Vote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference("user-vote") // to avoid duplicate references into Restaurant->User
    private User user;

    @NotNull
    @Positive
    private int restaurantId;

    @Column(name = "created", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime created = LocalDateTime.now();

    public Vote(User user, int restaurantId) {
        this.user = user;
        this.restaurantId = restaurantId;
    }
}