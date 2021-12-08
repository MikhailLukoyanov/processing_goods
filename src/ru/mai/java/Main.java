package ru.mai.java;

import ru.mai.java.algorithm.Algorithm;
import ru.mai.java.handler.Parser;
import ru.mai.java.logger.LoggerWrapper;
import ru.mai.java.new_file.FileCreator;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class Main {
    private static LoggerWrapper logger = new LoggerWrapper(Main.class.getName());

    public static void main(String[] args) {
        HashMap<String, ArrayList<Double>> groceryList = new HashMap<String, ArrayList<Double>>();
        try {
            FileCreator file = new FileCreator(logger);
            Writer out = file.getOutFile();
            InputStream stream = file.getInFile();
            Parser parser = new Parser(stream, logger);
            groceryList = parser.getAllProducts();
            Algorithm algorithm = new Algorithm(groceryList, out, logger);
            algorithm.productSearch();
            stream.close();
            out.close();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
