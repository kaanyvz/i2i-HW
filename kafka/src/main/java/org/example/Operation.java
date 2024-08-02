package org.example;

import java.io.Serializable;

public record Operation(
        int operand,
        String operation
) implements Serializable { }

