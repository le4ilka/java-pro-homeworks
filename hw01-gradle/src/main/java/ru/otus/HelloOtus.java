package ru.otus;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class HelloOtus {
    public static void main(String[] args) {
        System.out.println("Hello, Otus! hw");

        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("row1", "column1", 1);
        table.put("row1", "column2", 2);
        table.put("row2", "column1", 3);

        System.out.println("Table: " + table);
    }
}
