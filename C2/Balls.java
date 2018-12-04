package concurrent_assignment3.C2;

import java.awt.Color;
import javax.swing.*;

public class Balls {

    public static void nap(int ms) {
	try {
	    Thread.sleep(ms);
	}
	catch(InterruptedException e) {
	    //
	    //  Print out the name of the tread that caused this.
	    //
	    System.err.println("Thread "+Thread.currentThread().getName()+
			       " throwed exception "+e.getMessage());
	}
    }

    public static void main(String[] a) {
	CyclicBarrier barrier1=new CyclicBarrier(6);            //create a barrier
        final BallsWorld world = new BallsWorld();
	final JFrame win = new JFrame();
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.getContentPane().add(world);
		win.pack();
		win.setVisible(true);
	    }});
	
	Thread.currentThread().setName("MyMainThread");

	nap((int)(5000*Math.random()));
	new Ball(world, 50, 80, 5, 10, Color.red,barrier1).start();         //call it
	nap((int)(5000*Math.random())); 
	new Ball(world, 70, 100, 8, 6, Color.blue,barrier1).start();
	nap((int)(5000*Math.random()));
	new Ball(world, 150, 100, 9, 7, Color.green,barrier1).start();
	nap((int)(5000*Math.random()));
	new Ball(world, 200, 130, 3, 8, Color.black,barrier1).start();
	nap((int)(5000*Math.random()));
        new Ball(world, 220, 130, 1, 8, Color.yellow,barrier1).start();
	nap((int)(5000*Math.random()));
        new Ball(world, 400, 130, 20, 2, Color.ORANGE,barrier1).start();
	nap((int)(5000*Math.random()));
    }
}
