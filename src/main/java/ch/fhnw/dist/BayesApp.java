package main.java.ch.fhnw.dist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class BayesApp {

    public static void main(String[] args) {

        Ham ham = new Ham();
        System.out.println(ham.getFrequencies());

    }
}
