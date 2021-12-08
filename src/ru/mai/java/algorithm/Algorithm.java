package ru.mai.java.algorithm;

import ru.mai.java.logger.LoggerWrapper;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * Класс содержит логику и вычисления.
 */
public class Algorithm {

    private HashMap<String, ArrayList<Double>> products;
    private Writer out;
    private LoggerWrapper logger;

    public Algorithm(HashMap<String, ArrayList<Double>> products, Writer out, LoggerWrapper logger) {
        this.products = products;
        this.out = out;
        this.logger = logger;
    }

    /**
     * Проверяет название введенного товара с товарами, цены которых записаны, если такого товара нет, выводит сообщение
     */
    public void productSearch() {
        String sum;
        String[] arrSum;
        try {
            System.out.print("Введите название товара: ");
            Scanner scanner = new Scanner(System.in);
            long timerTwo = System.currentTimeMillis();
            String buffProduct = scanner.nextLine();
            if (!products.containsKey(buffProduct)) {
                out.write("Данных по товару " + "<" + buffProduct + ">" + " не найдено");
                logger.log(Level.WARNING, "Данных по товару " + "<" + buffProduct + ">" + " не найдено");
            } else {
                sum = totalAmount(products.get(buffProduct));
                arrSum = sum.split("\\.");
                if (arrSum[1].length() == 1) {
                    arrSum[1] = arrSum[1] + "0";
                }
                out.write("Общая стоимость товара " + "<" + buffProduct + ">" + ": " + "\"" + arrSum[0] + "руб\", " + arrSum[1] + "коп\".");
            }
            System.out.println(System.currentTimeMillis() - timerTwo + " мс");
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    /**
     * Складывает все цены одного из товаров
     * @param priceLine список цен одного товара
     * @return общая цена товара
     */
    private String totalAmount(ArrayList<Double> priceLine) {
        Double sum = 0.0;
        String arrSum;
        for (Double variables : priceLine) {
            sum += variables;

        }
        arrSum = Double.toString(sum);
        return arrSum;
    }
}
