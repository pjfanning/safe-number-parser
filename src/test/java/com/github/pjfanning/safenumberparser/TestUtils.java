package com.github.pjfanning.safenumberparser;

final class TestUtils {
    static String genLargeNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1050; i++) {
            sb.append('9');
        }
        return sb.toString();
    }
}
