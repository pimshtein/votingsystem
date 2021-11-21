package ru.java.votingsystem.web.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.java.votingsystem.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserTo {
    @NotBlank
    @Size(min = 2, max = 100)
    protected String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    private Set<Role> roles;
}
