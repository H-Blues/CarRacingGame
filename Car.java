package GUI.carRaceGame;

import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Car extends Thread{
    JLabel car;
    Random rand = new Random();
    int position_x;
    int position_y;

    public Car(JLabel car, int position_x){
        this.car = car;
        this.position_x = position_x;
        start();
    }

    public void run(){
        while(true) {
            position_x = rand.nextInt(250) + 150;
            position_y = -100;
            try{
                sleep(position_x * 5 + 1000);
            }catch (InterruptedException ex){
                Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
            }
            car.setBounds(position_x, position_y, 50, 50);
            while (car.getY() < 600){
                car.setBounds(position_x, position_y, 50, 50);
                position_y += 3;
                try{
                    sleep(position_x / 20);
                }catch (InterruptedException ex){
                    Logger.getLogger(Car.class.getName()).log(Level.SEVERE,null, ex);
                }
            }
        }
    }
}

