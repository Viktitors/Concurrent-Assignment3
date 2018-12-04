package concurrent_assignment3.B2;
/**
 * Remember to move the 'cars_images' folder to the root directory
 * of your project,
 * or write the absolute path to the folder in lines 23,35,27
 * in CarWorld.java. 
 * 
 * Actually, cars of the same color do not need to wait: they all move
 * in the same sense. So now the bridge will not only be safe but efficient.
 * 
 * Pablo.Bermejo@uclm.es
 *
 */
public class TrafficController {
    int blue=0;
    int red=0;
    
    public synchronized void redEnters() throws InterruptedException {
        while(blue!=0){wait();}
        red++;
    }

    public synchronized void blueEnters() throws InterruptedException {
	while(red!=0){wait();}
        blue++;
    }

     public synchronized  void blueExits() {
    	blue--;
        notifyAll();
    	 
    }

    public synchronized  void redExits() {
        red--;
        notifyAll();
	

    }

}
