package ru.mai.java.handler;

import ru.mai.java.logger.LoggerWrapper;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Класс работы с входными данными
 */
public class Parser {

    private static final String REGEX = "\"([А-Яа-я]+)\":\"(\\d+)руб\",\"(\\d+)коп\",?";
    private InputStream stream;
    private HashMap<String, ArrayList<Double>> products;
    private LoggerWrapper logger;

    public Parser(InputStream inputStream, LoggerWrapper logger) {
        this.stream = inputStream;
        this.logger = logger;
    }

    /**
     * Проверка корректности введенных данных и создание списка с ключами
     * @return список цен с названиями товара
     */
    public HashMap<String, ArrayList<Double>> getAllProducts() {
        Scanner scanner = new Scanner(stream);
        int line = 1;
        Pattern pattern = Pattern.compile(REGEX);

        products = new HashMap<>();
        long timerOne = System.currentTimeMillis();
        List<String> list = scanner.findAll(".+")
                .map(MatchResult::group)
                .collect(Collectors.toList());

        for (String str : list) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String name = matcher.group(1);
                if (!products.containsKey(name)) {
                    products.put(name, new ArrayList<>(Arrays.asList(Double.parseDouble(
                            matcher.group(2) + "." + matcher.group(3)
                    ))));
                } else {
                    Double price = Double.parseDouble(matcher.group(2) + "." + matcher.group(3));
                    products.get(name).add(price);
                }
            } else {
                try {
                    logger.log(Level.WARNING, "неверный формат данных " + "<" + str + ">" + " в строчке " + line + "\n");
                } catch (Exception e) {
                    logger.log(Level.WARNING, e.getMessage());
                }
            }
            line++;
        }
        System.out.println(System.currentTimeMillis() - timerOne + " мс");
        return products;
    }
}
