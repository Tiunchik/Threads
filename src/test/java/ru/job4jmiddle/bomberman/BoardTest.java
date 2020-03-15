package ru.job4jmiddle.bomberman;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class BoardTest {

    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream console = new PrintStream(out);

    @Before
    public void before() {
        System.setOut(console);
    }

    @After
    public void after() {
        System.setOut(System.out);
    }

    @Test
    public void whenWeSeeThatGameIsWork() throws InterruptedException {
        Board board = new Board(10);
        Player gamer = new Player(board);
        Thread thread00 = new Thread(gamer);
        thread00.start();
        Thread.sleep(5000);
        thread00.interrupt();
        assertTrue(out.toString().contains("Current position is"));
    }
}