package com.springhibernate.demo.util;

import org.springframework.stereotype.Component;

@Component
public class OnlyAcademicPurposes {

    public static final String SPACE = "\n";

    public String printSpaces() {
        return printSpaces(8);
    }

    private String printSpaces(int i) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sb.append(SPACE);
        }
        return sb.toString();
    }
}
