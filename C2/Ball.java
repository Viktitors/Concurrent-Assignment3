package concurrent_assignment3.C2;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

//import Ass3.ballsworld.BallsWorld.Balls;

public class Ball extends Thread {

    BallsWorld world;
    CyclicBarrier barrier;              //barrier atribbute
    private int xpos, ypos, vel, dirc;

    private final Color col;

    private final static int bolaw = 20;
    private final static int bolah = 20;
    
    public Ball(BallsWorld world, int xpos, int ypos,
		int vel, int dirc, Color col,CyclicBarrier barrier) {
	
	
	super("Ball :"+col);
        this.barrier=barrier;           //assign barrier
	this.world = world;
      	this.xpos = xpos; this.ypos = ypos;
	this.vel = vel; this.dirc = dirc;
        this.col = col;

        world.addBall(this);
    }
    
    public void run() {
	while (true)
	    move();
    }
   
    public void move() {
	if (xpos >= world.getWidth() - bolaw || xpos <= 0 ) vel = -vel;
	
	if (ypos >= world.getHeight() - bolah || ypos <= 0 ) dirc = -dirc;

	Balls.nap(30);
	doMove();
	world.repaint();
        
        /**********************************/
        /*Barrier must be used in here*/
        if (xpos >= ypos-2 && xpos <= ypos+2){
            
            try {
                this.vel=0;
                barrier.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
            }
	  
	  Balls.nap(1000);
          
        }   
        
    }
    
    public synchronized void doMove() {
	xpos += vel;
	ypos += dirc;
    }

    
    public synchronized void draw(Graphics g) {
	g.setColor(col);
	g.fillOval(xpos,ypos,bolaw,bolah);
    }
}
