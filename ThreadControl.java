package GUI.carRaceGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ThreadControl extends Thread{
    ArrayList<Obstacle> obstacles;
    JLabel car;
    JTextField points;
    int position_y = 100;
    int num = 0;

    ThreadControl(ArrayList obstacles, JLabel car, JTextField points){
        start();
        this.car = car;
        this.obstacles = obstacles;
        this.points = points;
    }

    public void failureNotice(){
        for(Obstacle obstacle: obstacles){
            obstacle.stop();
            car.disable();
        }

        JFrame frame = new JFrame();
        JButton b1 = new JButton("Exit Game");
        JButton b2 = new JButton("Cancel");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }});
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        Object[] options = {b1, b2};
        JOptionPane.showOptionDialog(frame,"Game Over! Your score: " + points.getText() , "CarRaceGame",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);
    }

    public void run(){
        while(true){
            points.setText("Score: " + String.valueOf(num++));
            for(Obstacle o: obstacles){
                position_y = o.position_y;
                boolean carLeftBoundary = car.getBounds().getX() >= o.position_x - 50;
                boolean carRightBoundary = car.getBounds().getX() <= o.position_x + 50;
                boolean carUpBoundary = car.getBounds().getY() > o.position_y - 150;
                boolean carBelowBoundary = car.getBounds().getY() < o.position_y + 100;
                if(carLeftBoundary && carRightBoundary && carUpBoundary && carBelowBoundary ){
                    failureNotice();
                    return;
                }
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}