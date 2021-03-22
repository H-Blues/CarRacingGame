package GUI.carRaceGame;

import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Obstacle extends Thread{
    JLabel obstacle;
    Random rand = new Random();
    public int position_x;
    public int position_y;

    public Obstacle(JLabel obstacle){
        this.obstacle = obstacle;
        start();
    }

    public void run(){
        while(true) {
            position_x = rand.nextInt(200) + 150;
            position_y = -300;
            double randNumOne = Math.random() * 300 + 200;
            double randNumTwo = Math.random() * 40 + 10;
            try{
                sleep(position_x ^3 );
            }catch (InterruptedException ex){
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
            obstacle.setBounds(position_x, position_y, 100, 200);
            while (obstacle.getY() < 700){
                obstacle.setBounds(position_x, position_y, 100, 200);
                position_y += 3;
                try{
                    sleep((long) (randNumOne / randNumTwo));
                }catch (InterruptedException ex){
                    Logger.getLogger(Car.class.getName()).log(Level.SEVERE,null, ex);
                }
            }
        }
    }
}

