package org.AM;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class AppTest3 {

    private static final int szerokosc = 800;
    private static final int wysokosc = 600;

    private static final int gracz_wysokosc = 100;
    private static final int gracz_szerokosc = 15;

    private static final double pilka_R = 15;

    private int pilkaYszybkosc = 1;
    private int pilkaXszybkosc = 1;

    private double gracz1pozY = wysokosc / 2;
    private double gracz2pozY = szerokosc / 2;

    private double pilkaPozX = szerokosc / 2;
    private double pilkaPozY = wysokosc / 2;

    private int punktyG1 = 0;
    private int punktyG2 = 0;

    private boolean startGry;

    private int gracz1PozX = 0;
    private double gracz2PozX = szerokosc - gracz_szerokosc;

    Canvas canvas = new Canvas(szerokosc, wysokosc);
    GraphicsContext gc = canvas.getGraphicsContext2D();

    @BeforeEach
    void setUp() {
    }

    @Test
    void launch() {
        gc.fillText(punktyG1 + "\t\t\t\t\t\t\t\t" + punktyG2, szerokosc / 2, 500);
    }
}