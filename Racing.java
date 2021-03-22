package GUI.carRaceGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.awt.event.KeyEvent.*;
import static java.lang.Thread.sleep;

public class Racing extends JFrame implements KeyListener {
    JLabel racetrack;
    static JLabel car;
    static JLabel obstacle_one;
    JTextField points;
    int score = 0;
    int x = 135;
    int y = 350;

    Racing(){
        super("CarRaceGame");
        constructFrame();
    }

    private void constructFrame(){
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        addKeyListener(this);

        //getResource里更改图片的位置和名称
        racetrack = new JLabel(new ImageIcon(Racing.class.getResource("images/race_track.jpg")));
        car = new JLabel(new ImageIcon(Racing.class.getResource("images/car.jpg")));
        obstacle_one = new JLabel(new ImageIcon(Racing.class.getResource("images/obstacle_one.png")));
        points = new JTextField();

        //setBounds函数，里面四个参数，x,y表示图片放的位置，width和hight表示图片的大小
        car.setBounds(x, y, 100, 200);
        obstacle_one.setBounds(160, -20,100,200);
        points.setBounds(480, 12, 100, 30);
        racetrack.add(car);
        racetrack.add(points);

        getContentPane().add(racetrack);
        racetrack.add(obstacle_one);
        pack();
        setVisible(true);
    }

    public void obstacleMove() {
        points.setText("Score: " + String.valueOf(score++));
        //setBounds里的 getY() + 10， 10是里面的速度控制，数值越大每一毫秒下降的距离越多
        obstacle_one.setBounds(obstacle_one.getX(), obstacle_one.getY()+10,100,200);
        if (obstacle_one.getY() > 900){
            //当障碍物在屏幕中消失之后，生成一个新的随机位置横坐标:obstacle_one_X
            //并将位置纵坐标设置成负数
            int obstacle_one_X = (int) (Math.random()*200+200);
            obstacle_one.setBounds(obstacle_one_X, -50, 100 ,200);
        }
    }

    public boolean judgeStop(){
        //判断小车有没有碰到障碍物
        boolean flag = false;

        boolean carLeftBoundary = car.getBounds().getX() >= obstacle_one.getX() - 50;
        boolean carRightBoundary = car.getBounds().getX() <= obstacle_one.getX() + 50;
        boolean carUpBoundary = car.getBounds().getY() >= obstacle_one.getY() - 100;
        boolean carBelowBoundary = car.getBounds().getY() <= obstacle_one.getY() + 100;

        if(carLeftBoundary && carRightBoundary && carUpBoundary && carBelowBoundary){
            Thread.interrupted();
            flag = true;
            //显示一个新的小窗口提示游戏结束
//            JFrame frame = new JFrame();
//            frame.setSize(300,200);
//            JButton jButton1 = new JButton("Exit");
//            JButton jButton2 = new JButton("Cancel");
//            jButton1.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    System.exit(0);
//                }
//            });
//            jButton2.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    frame.setVisible(false);
//                }
//            });
//            Object[] options = {jButton1, jButton2};
//            JOptionPane.showOptionDialog(frame,"Game Over! Your score: " + score, "Notice",
//                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }

        return flag;
    }

    public static void main(String[] args) {
        Racing racing = new Racing();
        while(!racing.judgeStop()){
            try {
                racing.obstacleMove();
                sleep(80);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == VK_RIGHT && x < 360) {car.setBounds(x += 10, y, 100, 100);}
        if(e.getKeyCode() == VK_LEFT && x > 135) {car.setBounds(x -= 10, y, 100, 100);}
        if(e.getKeyCode() == VK_UP && y > 0) {car.setBounds(x, y -= 10, 100, 100);}
        if(e.getKeyCode() == VK_DOWN && y < 900) {car.setBounds(x, y += 10, 100, 100);}}

    @Override
    public void keyReleased(KeyEvent e) { }

}
