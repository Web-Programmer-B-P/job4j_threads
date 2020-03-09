package ru.job4j.non.exception;

/**
 * Class OptimisticException
 *
 * @author Petr B.
 * @since 15.01.2020, 10:42
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String optimistic) {
        super(optimistic);
    }
}
