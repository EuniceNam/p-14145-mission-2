package com.back.util;

import java.nio.file.Path;
import java.util.Comparator;

public class FileSortUtil {
    public static Comparator<Path> byFileNameNumber() {
        return Comparator
                .comparingInt((Path path) -> Integer.parseInt(path.getFileName().toString().split("\\.")[0]))
                .reversed();
    }
}
