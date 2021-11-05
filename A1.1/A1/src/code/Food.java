package code;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Food implements Serializable {
    private Point coordinate = new Point();
    public static final int BLOCK_SIZE = 25;
    private static Image image = null;

    public Food(Snake s){
        image = new ImageIcon(this.getClass().getResource("/image/food.png")).getImage();
        // random coordinate, check it not covered the snake
        coordinate.x = (int) (Math.random() * 15) * BLOCK_SIZE;
        coordinate.y = (int) (Math.random() * 15) * BLOCK_SIZE;
        while (s.checkBody(coordinate)) {
            coordinate.x = (int) (Math.random() * 15) * BLOCK_SIZE;
            coordinate.y = (int) (Math.random() * 15) * BLOCK_SIZE;
        }

    }
    public Point getCoordinate() {
        return coordinate;
    }

    public void draw(Graphics g) {
        g.drawImage(image, coordinate.x, coordinate.y, BLOCK_SIZE, BLOCK_SIZE, null);
    }
}
