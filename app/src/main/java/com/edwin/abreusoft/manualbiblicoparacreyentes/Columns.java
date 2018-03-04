package com.edwin.abreusoft.manualbiblicoparacreyentes;

public class Columns {

    public static String[] getColumn(String[][] items, int index) {
        String[] column = new String[items.length];

        for (int i = 0; i < column.length; i++) {
            column[i] = items[i][index];
        }

        return column;
    }

}
