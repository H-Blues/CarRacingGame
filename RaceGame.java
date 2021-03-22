package GUI.carRaceGame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.awt.event.KeyEvent.*;

public class RaceGame extends JFrame implements KeyListener{
    JLabel racetrack;
    JLabel car;
    JLabel obstacle_one;
    JLabel obstacle_two;
    JLabel obstacle_three;
    JTextField points;
    int x = 135;
    int y = 350;

    RaceGame(){
        super("CarRaceGame");
        constructFrame();
        startRace();
    }

    private void constructFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocation(350,0);
        addKeyListener(this);

        racetrack = new JLabel(new ImageIcon(RaceGame.class.getResource("images/race_track.jpg")));
        car = new JLabel(new ImageIcon(RaceGame.class.getResource("images/car.jpg")));
        obstacle_one = new JLabel(new ImageIcon(RaceGame.class.getResource("images/obstacle_one.png")));
        obstacle_two = new JLabel(new ImageIcon(RaceGame.class.getResource("images/obstacle_two.png")));
        obstacle_three = new JLabel(new ImageIcon(RaceGame.class.getResource("images/obstacle_three.png")));
        points = new JTextField();

        car.setBounds(x, y, 100, 200);
        points.setBounds(480, 12, 100, 30);
        racetrack.add(car);
        racetrack.add(points);
        racetrack.add(obstacle_one);
        racetrack.add(obstacle_two);
        racetrack.add(obstacle_three);

        getContentPane().add(racetrack);
        pack();
        setVisible(true);
    }

    public void startRace(){
        ArrayList<Obstacle> obstacles = new ArrayList();
        Obstacle o1 = new Obstacle(obstacle_one);
        Obstacle o2 = new Obstacle(obstacle_two);
        Obstacle o3 = new Obstacle(obstacle_three);
        obstacles.add(o1); obstacles.add(o2); obstacles.add(o3);
        new ThreadControl(obstacles, car, points);

        while(true){
            try {
                Thread.sleep(70);
            } catch (InterruptedException ex) {
                Logger.getLogger(RaceGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        new RaceGame();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == VK_RIGHT && x < 360) {car.setBounds(x += 10, y, 100, 100);}
        if(e.getKeyCode() == VK_LEFT && x > 135) {car.setBounds(x -= 10, y, 100, 100);}
        if(e.getKeyCode() == VK_UP && y > 0) {car.setBounds(x, y -= 10, 100, 100);}
        if(e.getKeyCode() == VK_DOWN && y < 700) {car.setBounds(x, y += 10, 100, 100);}
    }

    @Override
    public void keyReleased(KeyEvent e){
    }

}
