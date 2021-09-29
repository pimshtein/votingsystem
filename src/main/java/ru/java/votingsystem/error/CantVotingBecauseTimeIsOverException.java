package ru.java.votingsystem.error;

public class CantVotingBecauseTimeIsOverException extends RuntimeException {
    public CantVotingBecauseTimeIsOverException() {
        super("User can't vote cause time is after 11.00 AM");
    }
}