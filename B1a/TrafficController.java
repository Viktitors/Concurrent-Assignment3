package concurrent_assignment3.B1a;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Remember to move the 'cars_images' folder to the root directory
 * of your project,
 * or write the absolute path to the folder in lines 23,35,27
 * in CarWorld.java. 
 * 
 * Use Semaphores to create a safe bridge (only 1 car on the bridge at 
 * the same time)
 * 
 * Pablo.Bermejo@uclm.es
 *
 */

public class TrafficController {
    Semaphore bridge  = new Semaphore(1);
     
    
    public void redEnters() {
        try {
            bridge.acquire();
        } catch (InterruptedException ex) {
           
        }
    }

    public  void blueEnters() {
        try {
            bridge.acquire();
        } catch (InterruptedException ex) {
            
        }
    }

     public  void blueExits() {
    	bridge.release();
    	 
    }

    public  void redExits() {
        bridge.release();
	

    }

}
