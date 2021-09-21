package ru.java.votingsystem.util;

import lombok.experimental.UtilityClass;
import ru.java.votingsystem.error.NotFoundException;

@UtilityClass
public class ValidationUtil {
    public static void checkModification(int count, int id) {
        if (count == 0) {
            throw new NotFoundException("Entity with id=" + id + " not found");
        }
    }
}