package code;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class GameClient {
    public static final int BLOCK_SIZE = 25; //block size 25*25
    public static final int ROW = 17; //block number
    public static final int COL = 17;
    private boolean printable = true; //continue or stop the game
    private JFrame frame = new JFrame();
    private JPanel menu = new JPanel();
    private DrawPanel game_panel = null;
    private JButton start_btn = null;
    private JButton exit_btn = null;
    private JButton save_btn = null;
    private JButton load_btn = null;
    private JButton stop_btn = null;
    private JButton continue_btn = null;
    private JLabel gamestate = null;
    private Snake player = new Snake();
    private Food food = new Food(player);
    private Thread paintT;

    public static void main(String[]args){
        new GameClient();
    }

    public GameClient() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(Color.DARK_GRAY);

        start_btn = new JButton("Start");
        exit_btn = new JButton("Exit");
        save_btn = new JButton("Save");
        load_btn = new JButton("Load");
        stop_btn = new JButton("Stop");
        continue_btn = new JButton("Cont.");

        gamestate = new JLabel(" ");
        gamestate.setBackground(Color.RED);

        start_btn.addActionListener(new MenuActionListener());
        exit_btn.addActionListener(new MenuActionListener());
        save_btn.addActionListener(new MenuActionListener());
        load_btn.addActionListener(new MenuActionListener());
        stop_btn.addActionListener(new MenuActionListener());
        continue_btn.addActionListener(new MenuActionListener());
        menu.add(start_btn);
        menu.add(exit_btn);
        menu.add(save_btn);
        menu.add(load_btn);
        menu.add(stop_btn);
        menu.add(continue_btn);
        menu.add(gamestate);

        frame.getContentPane().add(BorderLayout.EAST, menu);


        game_panel = new DrawPanel();
        game_panel.setBackground(Color.GRAY);
        game_panel.setSize(15*BLOCK_SIZE, 15*BLOCK_SIZE);
        game_panel.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        game_panel.setVisible(true);
        frame.getContentPane().add(BorderLayout.CENTER, game_panel);


        frame.setSize(ROW * BLOCK_SIZE+30,COL * BLOCK_SIZE-10);
        frame.setVisible(true);
    }

    class MenuActionListener implements ActionListener { //press the start button, game start
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == start_btn) {
                printable = true;
                player = new Snake();
                food = new Food(player);

                gamestate.setText(" ");
                menu.setFocusable(false);
                game_panel.setFocusable(true);
                game_panel.requestFocus();
                frame.revalidate();
                if (paintT != null) paintT.interrupt();
                paintT = new Thread(new PaintThread());
                paintT.start();
            } else if (e.getSource() == exit_btn) {
                System.exit(0);
            } else if (e.getSource() == save_btn) {
                FileOutputStream fileStream = null;
                ObjectOutputStream os = null;
                try {
                    fileStream = new FileOutputStream("save_data.dat");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                try {
                    os = new ObjectOutputStream(fileStream);
                    os.writeObject(player);
                    os.writeObject(food);
                    os.close();

                    menu.setFocusable(false);
                    game_panel.setFocusable(true);
                    game_panel.requestFocus();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            } else if (e.getSource() == load_btn) {
                FileInputStream fileStream = null;
                ObjectInputStream os;
                try {
                    fileStream = new FileInputStream("save_data.dat");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                try {
                    os = new ObjectInputStream(fileStream);
                    player = (Snake) os.readObject();
                    food = (Food) os.readObject();

                    menu.setFocusable(false);
                    game_panel.setFocusable(true);
                    game_panel.requestFocus();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            } else if (e.getSource() == stop_btn) {
                printable = false;
                gamestate.setText("Stop");
            } else if (e.getSource() == continue_btn) {
                printable = true;
                gamestate.setText(" ");
                menu.setFocusable(false);
                game_panel.setFocusable(true);
                game_panel.requestFocus();
                paintT = new Thread(new PaintThread());
                paintT.start();
            }
        }
    }

    public class PaintThread implements Runnable { //control the game speed, draw and refresh the game 2 times/s
        @Override
        public void run() {
            while (printable) {
                player.move();
                // Execute the move() denoting that the direction become the old direction, the game should
                // read the following pressed key

                if (player.eat(food)) { //check if snake eat food
                    player.grow();
                    food = new Food(player);
                    //if true, extends the snake, refresh the food
                }
                GameOver();

                frame.repaint();
                //draw the game

                try {
                    Thread.sleep(500);
                    //sleep 0.5s, monitor the key input at the same time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private void GameOver() {
        if (player.hit_wall()) {
            printable = false;
            gamestate.setText("Game Over");
            gamestate.setForeground(Color.red);
        }
        if (player.eat_itself()) {
            //check if snake eat itself
            printable = false;
            gamestate.setText("Game Over");
            gamestate.setForeground(Color.red);
            //if ture, end the game
        }
        if (player.full_length()) {
            printable = false;
            gamestate.setText("Win");
            gamestate.setForeground(Color.red);
        }
    }

    private class keyMonitor extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int KeyCode = e.getKeyCode();
            switch (KeyCode) {
                case KeyEvent.VK_UP:
                    System.out.println("P up");
                    if (player.getOld_direction() != Direction.D){
                        player.setDirection(Direction.U);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("P down");
                    if (player.getOld_direction() != Direction.U){
                        player.setDirection(Direction.D);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("P right");
                    if (player.getOld_direction() != Direction.L){
                        player.setDirection(Direction.R);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("P left");
                    if (player.getOld_direction() != Direction.R){
                        player.setDirection(Direction.L);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private class DrawPanel extends JPanel {
        public DrawPanel(){

            this.addKeyListener(new keyMonitor());
        }
        public void paintComponent(Graphics g) {
            //draw snake, the snake would move first then be drawn
            player.draw(g);
            //draw food
            food.draw(g);
        }
    }


}
