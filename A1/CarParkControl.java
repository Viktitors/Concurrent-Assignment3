/**
 * //adapted by miguel.galdon@uclm.es
 */
package concurrent_assignment3-Viktitors.A1;

/**
 * You can only park if there is at least 1 free slot.
 * You can only leave if there is there is at least 1 parked car.
 * Create and use semaphores as appropriate.
 * @author Pablo.Bermejo@uclm.es
 */
import java.util.concurrent.Semaphore;


class CarParkControl {

    protected int spaces;
    protected int capacity;
    NumberCanvas cont_;
    Counter counter;
    Semaphore cars;
    Semaphore emptySlots;
    Semaphore mutex=new Semaphore(1);
    CarParkControl(int n) {
        capacity = spaces = n;
        
        cars=new Semaphore(0);
        emptySlots=new Semaphore(this.capacity);
    }
    
    CarParkControl(int n,NumberCanvas cont) {
        cont_ = cont;
        capacity = spaces = n;
        counter = new Counter(cont_);
        counter.show(spaces);
        
        cars=new Semaphore(0);
        emptySlots=new Semaphore(this.capacity);
    }
     

    void arrive() throws InterruptedException {
       
        emptySlots.acquire();//first we say that we want to go in, then we wait for the mutual exclusion to let us go on
        try{
           mutex.acquire();  
       }catch(InterruptedException ex){}
        --spaces;
        counter.show(spaces); 
        mutex.release();
        cars.release();
        
    }

    void leaves() throws InterruptedException{
        
        cars.acquire();
        try{
           mutex.acquire();  
       }catch(InterruptedException ex){}
        ++spaces;
        counter.show(spaces);
        mutex.release();
        emptySlots.release();
        
     }
}


/********************LLEGADAS*******************************/
class Arriving implements Runnable {
    
    CarParkControl carpark;
    NumberCanvas display;
    
    
    Arriving(CarParkControl c) {
        carpark = c;
    }
    Arriving(CarParkControl c, NumberCanvas n) {
        carpark = c;
        display = n;
    }
    public void run() {
      try {
        while(true) {
          ThreadPanel.rotate(340); 
           carpark.arrive();
          ThreadPanel.rotate(20);           
        }
      } catch (InterruptedException e){}
    }
}

/********************SALIDAS*******************************/

class Leaving implements Runnable {

    CarParkControl carpark;
    NumberCanvas display;

    Leaving(CarParkControl c) {
        carpark = c;
    }
    
    public void run() {
      try {
        while(true) {
          ThreadPanel.rotate(20);
          carpark.leaves();
          ThreadPanel.rotate(340);        
        }
      } catch (InterruptedException e){}
    }
}
/********************COUNTER*******************************/

 class Counter {

    volatile int value=0;
    NumberCanvas display;

   
    Counter(NumberCanvas n) {
        display=n;
    }
    
    void show() {
       display.setvalue(value);   

    }
    
    void show(int n) {
        value = n;
        display.setvalue(value);   
    }
}
