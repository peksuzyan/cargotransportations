package com.tsystems.cargotransportations;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Draft {
    public static void main(String[] args) {
        String line = "[Moscow, Saint-Petersburg, Noviy Urengoy]";
        List<String> cities = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(line.substring(1, line.length() - 1), ",");
        while (tokenizer.hasMoreTokens()) {
            cities.add(tokenizer.nextToken().trim());
        }
        for (String s : cities) {
            System.out.println(s);
        }
    }
}
