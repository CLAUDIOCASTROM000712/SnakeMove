import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.WindowConstants;

class Board extends JPanel implements ActionListener{
    Snake snake;
    //snake=new Snake();
    public static void main(String args[]){
        Board b=new Board();
    }
    public Board(){
        setPreferredSize(new Dimension(Config.SIZE_WIN_W,Config.SIZE_WIN_H));
        setBackground(Color.GRAY);

        JFrame f=new JFrame("SNAKE");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(Config.SIZE_WIN_W,Config.SIZE_WIN_H);
        f.add(this);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.add(this);
        Timer t=new Timer(1000,this);
        t.start();
        snake=new Snake();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(snake.getColor());
        for(Point p:snake.getBody()){
            g.fillRect(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,Config.SIZE_SEG,Config.SIZE_SEG);
        }
        for(int i=0;i<Config.SIZE_WIN_W;i+=Config.SIZE_SEG){
            g.drawLine(i,0,i,Config.SIZE_WIN_H);
        }
        for(int j=0;j<Config.SIZE_WIN_H;j+=Config.SIZE_SEG){
            g.drawLine(0,j,Config.SIZE_WIN_W,j);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        ArrayList<Point> body=new ArrayList<Point>();
        body=snake.getBody();
        int head=0;
        for(int i=body.size()-1;i>0;i--){
            int x=body.get(i-1).getX();
            body.get(i).setX(x);

            int y=body.get(i-1).getY();
            body.get(i).setY(y);
        }
        int y=body.get(head).getY();
        y+=1;
        body.get(head).setY(y);
        repaint();
    }

}

class Snake{
    private Color color;
    private ArrayList<Point> body=new ArrayList<Point>();
    private int speed;

    public Snake(){
        body.add(new Point(6,1));
        body.add(new Point(5,1));
        body.add(new Point(4,1));
        color=Color.BLACK;
        speed=1;
    }

    public ArrayList<Point> getBody(){
        return body;
    } 
    public Color getColor(){
        return color;
    }
    public int getSpeed(){
        return speed;
    }

}

class Food{
    private Color color;
    private int size;
    private Point p;

    public Food(int size,Color color,Point p){
        this.size=size;
        this.color=color;
        this.p=p;
    }

    public void setPoint(Point p){
        this.p=p;
    }
    public Point getPoint(){
        return p;
    }

    public void randomNewFood(){
        double x=Math.random()*Config.SIZE_X_SEG;
        double y=Math.random()*Config.SIZE_Y_SEG;
        p.setX((int)x);
        p.setY((int)y);
    }
    public void draw(Graphics g){
        randomNewFood();
        g.setColor(color);
        g.fillOval(p.getX()*Config.SIZE_SEG,p.getY()*Config.SIZE_SEG,size,size);
    }

}

class Point{
    private int x;
    private int y;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

}

class Config{
    public static final int SIZE_SEG=50;
    public static final int SIZE_WIN_H=500;
    public static final int SIZE_WIN_W=600;
    public static final int SIZE_X_SEG=SIZE_WIN_W/SIZE_SEG;
    public static final int SIZE_Y_SEG=SIZE_WIN_H/SIZE_SEG;
}
