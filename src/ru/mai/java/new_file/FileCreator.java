package ru.mai.java.new_file;

import ru.mai.java.logger.LoggerWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

/**
 * Класс работы с файлами входных и выходных данных
 */
public class FileCreator {
    private final String INPUT_FILE = "input.txt";
    private final String OUTPUT_FILE = "output.txt";
    private LoggerWrapper logger;
    private Writer out;
    private InputStream stream;

    public FileCreator(LoggerWrapper logger) {
        this.logger = logger;
    }

    /**
     * Возвращает выходной поток
     * @return выходной поток
     */
    public Writer getOutFile() {
        try {
            File file = new File(OUTPUT_FILE);
            if (!file.exists()) {
                logger.log(Level.WARNING, "Файл, который указан к чтению или записи не найден. Создаем этот файл." + "\n");
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(OUTPUT_FILE), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Произошла ошибка при чтении/записи файла ввода/вывода");
        }
        catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return out;
    }

    /**
     * Возвращает входной поток
     * @return входной поток
     */
    public InputStream getInFile() {
        try {
            stream = new FileInputStream(INPUT_FILE);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return stream;
    }

}
