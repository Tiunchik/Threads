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
     * main - execute download from my own github.
     *
     * @param args https://github.com/Tiunchik/Job4j_middle_threads/blob/master/checkstyle.xml 200
     */
    public static void main(String[] args) {
        Config conf = new Config();
        conf.getConfig(args);
        try (BufferedInputStream readURL = new BufferedInputStream(new URL(conf.getURL()).openStream());
             FileOutputStream writeFile = new FileOutputStream(conf.getName())) {
            int limit = ((double) KB / conf.getLimit() < 1) ? 0 : KB / conf.getLimit();
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
}
