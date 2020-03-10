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
    private String[] param;
    /**
     * number of file for downloading
     */
    private int count = 0;

    /**
     * convert args of main program to parameters of program.
     *
     * @param parametrs args
     */
    public void getConfig(String[] parametrs) {
        int limit = parametrs.length - 1;
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            while (parametrs.length < 2 || !parametrs[limit].matches(SPEED)) {
                System.out.println("Please write parametrs again, last parametr have to be speed limit");
                String para = console.readLine();
                parametrs = para.split(SPACE);
            }
            for (int index = 0; index < limit; index++) {
                while (!parametrs[index].matches(URL)) {
                    System.out.println("Please write url parameter again, it has to be started from \"http\"");
                    parametrs[index] = console.readLine();
                }
            }
            while (!parametrs[limit].matches(SPEED)) {
                System.out.println("Please write speed limit again, it has to contain only numbers");
                parametrs[limit] = console.readLine();
            }
            param = new String[parametrs.length];
            System.arraycopy(parametrs, 0, param, 0, parametrs.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * return URL gotten from getconfig.
     *
     * @return URL
     */
    public String getURL() {
        return param[count];
    }

    /**
     * return speed limit gotten from getconfig.
     *
     * @return speed limit
     */
    public int getLimit() {
        return Integer.parseInt(param[param.length - 1]);
    }

    /**
     * return name for saved file.
     *
     * @return name
     */
    public String getName() {
        String answer;
        int index = param[count].lastIndexOf("/");
        if (index > 0) {
            answer = param[count].substring(index);
        } else {
            answer = "save" + count;
        }
        return answer;
    }

    public void next() {
        count++;
    }
}
