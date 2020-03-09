/**
 * Package ru.job4jmiddle.downloader for
 *
 * @author Maksim Tiunchik
 */
package ru.job4jmiddle.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class Config - implemented for work with args.
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 09.03.2020
 */
public class Config {
    /**
     * word separator.
     */
    private final static String SPACE = " ";
    /**
     * simple pattern for matching url.
     */
    private final static String URL = "^https.*";
    /**
     * pattern for matching numbers.
     */
    private final static String SPEED = "^[0-9].*$";
    /**
     * contains information after execution getConfig method.
     */
    private final String[] param = new String[3];

    /**
     * convert args of main program to parameters of program.
     *
     * @param parametrs args
     */
    public void getConfig(String[] parametrs) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            while (parametrs.length < 2) {
                System.out.println("Please write parametrs again");
                String para = console.readLine();
                parametrs = para.split(SPACE);
            }
            while (!parametrs[0].matches(URL)) {
                System.out.println("Please write url parametr again, it has to be started from \"http\"");
                parametrs[0] = console.readLine();
            }
            while (!parametrs[1].matches(SPEED)) {
                System.out.println("Please write speed limit again, it has to contain only numbers");
                parametrs[1] = console.readLine();
            }
            param[0] = parametrs[0];
            param[1] = parametrs[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = param[0].lastIndexOf("/");
        if (index > 0) {
            param[2] = param[0].substring(index);
        } else {
            param[2] = "save";
        }
    }

    /**
     * return URL gotten from getconfig.
     *
     * @return URL
     */
    public String getURL() {
        return param[0];
    }

    /**
     * return speed limit gotten from getconfig.
     *
     * @return speed limit
     */
    public int getLimit() {
        return Integer.parseInt(param[1]);
    }

    /**
     * return name for saved file.
     *
     * @return name
     */
    public String getName() {
        return param[2];
    }
}
