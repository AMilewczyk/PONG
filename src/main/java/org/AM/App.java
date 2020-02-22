package org.AM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

//PONG//

public class App extends Application {

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

    public void start(Stage stage) throws Exception {
        stage.setTitle("P O N G");

        //tlo
        Canvas canvas = new Canvas(szerokosc, wysokosc);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Oś czasu JavaFX = animacja w dowolnej formie zdefiniowana przez KeyFrames i ich czas trwania
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));

        // liczba cykli w animacji INDEFINITE = powtarzana w nieskończoność
        tl.setCycleCount(Timeline.INDEFINITE);

        //kontrolowanie myszy
        canvas.setOnMouseMoved(e ->  gracz1pozY  = e.getY());
        canvas.setOnMouseClicked(e ->  startGry = true);
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
        tl.play();
    }

    private void run(GraphicsContext gc) {
        // ustawienie grafiki
        gc.setFill(Color.ORANGE);
        //ustawienie koloru tla /
        gc.fillRect(0, 0, szerokosc, wysokosc);

        //ustawienie kolor paletki
        gc.setFill(Color.RED);
        gc.setFont(Font.font(25));

        if(startGry) {
            // ruch pilki
            pilkaPozX+=pilkaXszybkosc;
            pilkaPozY+=pilkaYszybkosc;

            // komputer ktory sledzi ruch pilki
            if(pilkaPozX < szerokosc - wysokosc  / 4) {
                gracz2pozY = pilkaPozY - gracz_wysokosc / 2;
            }  else {
                gracz2pozY =  pilkaPozY > gracz2pozY + gracz_wysokosc / 2 ?gracz2pozY += 1: gracz2pozY - 1;
            }


            //narysuj pilke
            gc.fillOval(pilkaPozX, pilkaPozY, pilka_R, pilka_R);
            gc.setFill(Color.BLUE);

        } else {
            //tekst startu
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("GRAJ!!- kliknij", szerokosc / 2, wysokosc / 4);

            //resetowanie pozycji pilki
            pilkaPozX = szerokosc / 2;
            pilkaPozY = wysokosc / 2;

            //resetowanie predkosci pilki i kierunku
            pilkaXszybkosc = new Random().nextInt(2) == 0 ? 1: -1;
            pilkaYszybkosc = new Random().nextInt(2) == 0 ? 1: -1;
        }

        // upewnia sie ze pilka pozostaje w obszarze
        if(pilkaPozY > wysokosc || pilkaPozY < 0) pilkaYszybkosc *=-1;

        // punkt dla komputera
        if(pilkaPozX < gracz1PozX - gracz_szerokosc) {
            punktyG2++;
            startGry = false;
        }

        // punkt dla czlowieka
        if(pilkaPozX > gracz2PozX + gracz_szerokosc) {
            punktyG1++;
            startGry = false;
        }

        //  zwiększyć prędkość po tym, jak piłka uderzy gracza
        if( ((pilkaPozX + pilka_R > gracz2PozX) && pilkaPozY >= gracz2pozY && pilkaPozY <= gracz2pozY + gracz_wysokosc) ||
                ((pilkaPozX < gracz1PozX + gracz_szerokosc) && pilkaPozY >= gracz1pozY && pilkaPozY <= gracz1pozY + gracz_wysokosc)) {
            pilkaYszybkosc += 1 * Math.signum(pilkaYszybkosc);
            pilkaXszybkosc += 1 * Math.signum(pilkaXszybkosc);
            pilkaXszybkosc *= -1;
            pilkaYszybkosc *= -1;
        }

        //2yswietl wynik
        gc.fillText(punktyG1 + "\t\t\t\t\t\t\t\t" + punktyG2, szerokosc / 2, 500);

        //wyswietl zawodnikow
        gc.fillRect(gracz2PozX, gracz2pozY, gracz_szerokosc, gracz_wysokosc);
        gc.fillRect(gracz1PozX, gracz1pozY, gracz_szerokosc, gracz_wysokosc);
    }

    // start aplikacji
    public static void main(String[] args) {
        launch(args);
    }
}

