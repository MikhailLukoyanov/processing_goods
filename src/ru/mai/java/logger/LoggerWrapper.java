package ru.mai.java.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Класс создания логирования
 */
public class LoggerWrapper {

    private Logger logger;

    public LoggerWrapper(String name) {
        logger = getLogger(name);
    }

    /**
     * Создает логгер для какого-то процесса с созданием файла логирования для этого процесса
     * @param name название процесса
     * @return логгер процесса
     */
    private Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        try {
            FileHandler fh = new FileHandler(name + ".log");
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);
        } catch (SecurityException e) {
            System.out.println("Не удалось создать файл лога" + name + ".log" + " из-за политики безопасности.");
        } catch (IOException e) {
            System.out.println("Не удалось создать файл лога" + name + ".log" + " из-за ошибки ввода-вывода.");
        }
        return logger;
    }

    public void log(Level warning, String message) {
        logger.log(warning, message);
    }
}
