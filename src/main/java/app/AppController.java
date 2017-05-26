package app;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import player.VideoPlayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AppController {
AnimationTimer at;

    @FXML
    public BorderPane pane;

    @FXML
    public GridPane gridPane;

    private VideoPlayer player;

    @FXML
    public void playMedia() {
        player = new VideoPlayer("test");
        gridPane.getChildren().add(player);
        player.play("rtsp://195.209.107.130:58554/");
        player.setVolume(1);
    }

    @FXML
    public void stopMedia() {
        if (player != null) {
            player.release();
        }
    }
//
    @FXML
    public void initialize(){
        final BufferedImage[] bi = new BufferedImage[1];
        at = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if(player!=null && player.getMp().getSnapshot()!=null) {
                bi[0] =player.getMp().getSnapshot();
                File outputfile = new File("1" + "." + "jpg");
                try {
                    ImageIO.write(player.getMp().getSnapshot(), "jpg", outputfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    at.start();
    }
}
