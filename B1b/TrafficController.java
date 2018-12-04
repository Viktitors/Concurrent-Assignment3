package concurrent_assignment3-Viktitors.B1b;

/**
 * Remember to move the 'cars_images' folder to the root directory
 * of your project,
 * or write the absolute path to the folder in lines 23,35,27
 * in CarWorld.java. 
 * 
 * Use signals to create a safe bridge (only 1 car on the bridge at 
 * the same time)
 * 
 * Pablo.Bermejo@uclm.es
 *
 */
public class TrafficController {
    int bridge=0;
     
    
    synchronized public void redEnters() throws InterruptedException {
        while(bridge==1)wait();
        bridge++;
        
    }

    synchronized public  void blueEnters() throws InterruptedException {
	while(bridge==1)wait();
        bridge++;
       
    }

     synchronized public  void blueExits() {
    	
         bridge--;
    	 notify();
    }

    synchronized public  void redExits() {
        bridge--;
	notify();

    }

}
