/**
 * Package ru.job4jmiddle.downloader for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;


/**
 * Class Downloader - download file from internet with URL.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 09.03.2020
 */
public class Downloader {
    /**
     * inner logger.
     */
    private static final Logger LOG = LogManager.getLogger(Downloader.class.getName());
    /**
     * contains number of bytes for one kilobyte.
     */
    private final static int KB = 1024;
    /**
     * pattern for matching numbers.
     */
    private final static String SPEED = "^[0-9].*$";


    private void download(String URL, String name, int downLimit) {
        try (BufferedInputStream readURL = new BufferedInputStream(new URL(URL).openStream());
             FileOutputStream writeFile = new FileOutputStream(name)) {
            int limit = ((double) KB / downLimit < 1) ? 0 : KB / downLimit;
            byte[] dataBuffer = new byte[KB];
            int readData;
            while ((readData = readURL.read(dataBuffer, 0, KB)) != -1) {
                writeFile.write(dataBuffer, 0, readData);
                Thread.sleep(limit);
            }
        } catch (IOException | InterruptedException e) {
            LOG.error("Download error", e);
        }
    }


    /**
     * main - execute download from my own github.
     *
     * @param args https://github.com/Tiunchik/Job4j_middle_threads/blob/master/checkstyle.xml
     *             https://github.com/Tiunchik/Job4j_middle_threads/blob/master/README.md
     *             200
     */
    public static void main(String[] args) {
        Config conf = new Config();
        conf.getConfig(args);
        while (!conf.getURL().matches(SPEED)) {
            new Downloader().download(conf.getURL(), conf.getName(), conf.getLimit());
            conf.next();
        }
    }
}
