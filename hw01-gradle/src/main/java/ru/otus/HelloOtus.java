package ru.otus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloOtus {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloOtus.class);

    public static void main(String[] args) {
        LOGGER.debug("Hello otus hw debug");

        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("row1", "column1", 1);
        table.put("row1", "column2", 2);
        table.put("row2", "column1", 3);

        LOGGER.atInfo()
                .setMessage("Good buy Otus Info{}")
                .addArgument(() -> table)
                .log();
        LOGGER.debug("Hello debug {}", table);
        LOGGER.atDebug()
                .setMessage("Good buy Otus Debug{} debug")
                .addArgument(() -> table)
                .log();
    }
}
