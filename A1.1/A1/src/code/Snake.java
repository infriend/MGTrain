package code;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Snake implements Serializable {
    private ArrayList<Point> body = new ArrayList<Point>();
    private Point tail = new Point();
    private static Image[] images = new Image[5];
    private Direction old_direction = Direction.R;
    private Direction direction = Direction.R;
    public static final int BLOCK_SIZE = 25;


    public Snake() {
        images[0] = new ImageIcon(this.getClass().getResource("/image/body.png")).getImage();
        images[1] = new ImageIcon(this.getClass().getResource("/image/up.png")).getImage();
        images[2] = new ImageIcon(this.getClass().getResource("/image/down.png")).getImage();
        images[3] = new ImageIcon(this.getClass().getResource("/image/right.png")).getImage();
        images[4] = new ImageIcon(this.getClass().getResource("/image/left.png")).getImage();
        // snake with a head and 2 bodies, head coordinate 20, 20
        body.add(new Point(7 * BLOCK_SIZE,7 * BLOCK_SIZE));
        body.add(new Point(6 * BLOCK_SIZE,7 * BLOCK_SIZE));
        body.add(new Point(5 * BLOCK_SIZE,7 * BLOCK_SIZE));
    }

    public void draw(Graphics g) { //draw snake in PaintThread
        for (int i = 0; i < body.size(); i++) {
            if (i == 0) {
                switch (direction) {
                    case L:
                        g.drawImage(images[4], body.get(i).x, body.get(i).y, BLOCK_SIZE, BLOCK_SIZE, null);
                        break;
                    case R:
                        g.drawImage(images[3], body.get(i).x, body.get(i).y, BLOCK_SIZE, BLOCK_SIZE, null);
                        break;
                    case U:
                        g.drawImage(images[1], body.get(i).x, body.get(i).y, BLOCK_SIZE, BLOCK_SIZE, null);
                        break;
                    case D:
                        g.drawImage(images[2], body.get(i).x, body.get(i).y, BLOCK_SIZE, BLOCK_SIZE, null);
                        break;
                    default:
                        break;
                }
            } else {
                g.drawImage(images[0], body.get(i).x, body.get(i).y, BLOCK_SIZE, BLOCK_SIZE, null);
            }
        }
    }

    public void move() { // update the coordinate of the body node
        Point head = body.get(0); //temp head
        tail = body.get(body.size()-1); //save the state of tail
        for (int i = body.size()-1; i > 0; i--) {
            body.set(i, new Point(body.get(i-1).x, body.get(i-1).y)); //update the body coordinate
        }
        switch (direction) {
            case U:
                head.move(head.x, head.y - BLOCK_SIZE);
                body.set(0, head);
                break;
            case D:
                head.move(head.x, head.y + BLOCK_SIZE);
                body.set(0, head);
                break;
            case R:
                head.move(head.x + BLOCK_SIZE, head.y);
                body.set(0, head);
                break;
            case L:
                head.move(head.x - BLOCK_SIZE, head.y);
                body.set(0, head);
                break;
        } //update the head coordinate
        System.out.printf("%d, %d\n", body.get(0).x/BLOCK_SIZE, body.get(0).y/BLOCK_SIZE);
        old_direction = direction;
    }

    public boolean checkBody(Point p) {
        //check if the point coordinate is on snake
        for (int i = 0; i < body.size(); i++) {
            if (p.x == body.get(i).x && p.y == body.get(i).y) {
                System.out.println("On body");
                return true; //if is on body
            }
        }
        return false;
    }

    public boolean eat(Food f) {
        if (f.getCoordinate().x == body.get(0).x && f.getCoordinate().y == body.get(0).y) {
            return true;//if eat
        } else {
            return false;
        }
    }

    public boolean full_length() {
        if (body.size() == 225) return true;
        else return false;
    }

    public void grow(){
        body.add(tail);
    }

    public boolean eat_itself(){
        for (int i = 1; i < body.size(); i++) {
            if (body.get(0).x == body.get(i).x && body.get(0).y == body.get(i).y) return true;
        }
        return false;
    }

    public boolean hit_wall() {
        if (body.get(0).x > BLOCK_SIZE*14 || body.get(0).x < 0) {
            return true;
        }
        if (body.get(0).y > BLOCK_SIZE*14 || body.get(0).y < 0) {
            return true;
        }
        return false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getOld_direction() {
        return old_direction;
    }

    public void setOld_direction(Direction old_direction) {
        this.old_direction = old_direction;
    }
}
