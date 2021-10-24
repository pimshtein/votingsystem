package ru.java.votingsystem.web.user.request;

import ru.java.votingsystem.model.User;

public class RequestMapper {

    public static User createUserFromTo(CreateUserTo userTo) {
        return new User(userTo.getName(), userTo.getEmail(), userTo.getPassword(), userTo.getRoles());
    }

    public static User createUserFromUpdateTo(UpdateUserTo userTo) {
        return new User(userTo.getName(), userTo.getEmail(), userTo.getPassword(), userTo.getRoles());
    }
}
