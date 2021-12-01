package main.java.com.masters.eleven;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws Exception {
        write();
        read();
    }

    private static void write() throws Exception {
        Path fileToWrite = Paths.get("C:/samples/java11Exer.txt");
        Files.writeString(fileToWrite, "\n\rThis\nis my first   \rjava 11 exercise!\r\u2005", StandardOpenOption.CREATE);
    }

    private static void read() throws Exception {
        Path fileToRead = Paths.get("C:/samples/java11Exer.txt");
        String content = Files.readString(fileToRead);
        content.lines().filter(Predicate.not(String::isBlank)).forEach(c -> System.out.println(c.strip().concat(" ").repeat(2)));
    }
}
