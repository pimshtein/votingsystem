package ru.java.votingsystem.web.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.java.votingsystem.model.Role;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserTo {
    protected String name;

    private String email;

    private String password;

    private Set<Role> roles;
}
