package com.github.pjfanning.safenumberparser;

/**
 * An exception thrown when a constraint is violated.
 */
public class ConstraintException extends Exception {
    public ConstraintException(String message) {
        super(message);
    }
}
