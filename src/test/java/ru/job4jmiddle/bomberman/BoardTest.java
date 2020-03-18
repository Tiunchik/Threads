package ru.job4jmiddle.bomberman;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        Board board = new Board(30);
        Walls walls = new Walls(board, 10);
        Player gamer = new Player(board);
        Monster monstr00 = new Monster(board);

        Thread blocks = new Thread(walls);
        blocks.start();
        Thread player = new Thread(gamer);
        player.start();
        Thread mnstr00 = new Thread(monstr00);
        mnstr00.setName("Monster00");
        mnstr00.start();
        Thread mnstr01 = new Thread(monstr00);
        mnstr01.setName("Monster01");
        mnstr01.start();

        Thread.sleep(4000);

        assertTrue(out.toString().contains("Player is on position"));
        assertTrue(out.toString().contains(mnstr00
                .getName() + " is on position"));
        assertTrue(out.toString().contains(mnstr01
                .getName() + " is on position"));

        blocks.interrupt();
        player.interrupt();
        mnstr00.interrupt();
    }
}