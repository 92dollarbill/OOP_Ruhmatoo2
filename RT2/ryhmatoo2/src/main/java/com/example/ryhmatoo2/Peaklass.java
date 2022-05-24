package com.example.ryhmatoo2;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;

// programmi jooksutamiseks käivitage seda klassi
public class Peaklass extends Application {
    boolean leiab;
    boolean kirjutab;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Sõnastik dict = new Sõnastik("src/main/Terminid.txt");

        Group juur = new Group();

        Button algusesse = new Button("Algusesse tagasi");
        algusesse.setTranslateX(185);
        algusesse.setTranslateY(250);

        Text kysimus = new Text();
        kysimus.setFill(Color.WHITE);
        kysimus.setY(150);

        TextField sona = new TextField();

        Text vastus = new Text();
        vastus.setFill(Color.WHITE);
        vastus.setY(200);

        Button sisesta = new Button("Sisesta");
        sisesta.setTranslateX(215);
        sisesta.setTranslateY(250);

        Button leida = new Button("Soovin sõnale leida inglisekeelset vastet");
        leida.setTranslateX(140);
        leida.setTranslateY(150);
        juur.getChildren().add(leida);

        Button kirjutada = new Button("Soovin faili kirjutada sõnu ja nende vasteid");
        kirjutada.setTranslateX(133);
        kirjutada.setTranslateY(250);
        juur.getChildren().add(kirjutada);

        // leiab sõnastikust vaste eestikeelsele sõnale
        leida.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                leiab = true;

                juur.getChildren().clear();

                kysimus.setText("Kirjuta lahtrisse sõna, millele tahad vastet leida");
                kysimus.setX(140);
                juur.getChildren().add(kysimus);

                sona.setTranslateX(170);
                sona.setTranslateY(200);
                juur.getChildren().add(sona);

                juur.getChildren().add(sisesta);
            }
        });

        // mitme sõna tõlkimise soovi korral kirjutab sõnad ja nende vasted faili valitud.txt
        kirjutada.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                kirjutab = true;

                juur.getChildren().clear();

                kysimus.setText("Kirjuta lahtrisse sõnad, mida tahad faili kirjutada. Eralda sõnad semikooloniga.");
                kysimus.setX(45);
                juur.getChildren().add(kysimus);

                sona.setTranslateX(170);
                sona.setTranslateY(200);
                juur.getChildren().add(sona);

                juur.getChildren().add(sisesta);
            }
        });

        sisesta.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // leiab soovitavale sõnale vaste
                if (leiab) {
                    leiab = false;

                    try {
                        juur.getChildren().clear();

                        String leitav = sona.getText();
                        String vaste = dict.leia(leitav);

                        vastus.setText(vaste);
                        vastus.setX(215);
                        juur.getChildren().add(vastus);

                    } catch (Exception e) {
                        Text probleem = new Text(e.toString());
                        probleem.setX(70);
                        probleem.setY(200);
                        probleem.setFill(Color.WHITE);
                        juur.getChildren().add(probleem);
                    }

                // kirjutab ette antud sõnad ja nende vasted faili valitud.txt
                } else if (kirjutab) {
                    kirjutab = false;

                    try {
                        juur.getChildren().clear();

                        String[] lisatav = sona.getText().split(";");
                        dict.kirjuta(lisatav);

                        vastus.setText("Sõnad on edukalt faili kirjutatud.");
                        vastus.setX(157);
                        juur.getChildren().add(vastus);

                    } catch (Exception e) {
                        Text probleem = new Text(e.toString());
                        probleem.setX(50);
                        probleem.setY(200);
                        probleem.setFill(Color.WHITE);
                        juur.getChildren().add(probleem);
                    }
                }

                juur.getChildren().add(algusesse);
            }
        });

        // läheb algsete valikute juurde tagasi
        algusesse.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                juur.getChildren().clear();
                juur.getChildren().add(leida);
                juur.getChildren().add(kirjutada);
            }
        });

        Scene stseen = new Scene(juur, 500, 500, Color.BLUE);
        primaryStage.setTitle("OOP sõnavara tõlkija");
        primaryStage.setScene(stseen);
        primaryStage.show();

    }
}
