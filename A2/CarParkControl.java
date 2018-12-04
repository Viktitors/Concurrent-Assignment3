/**
 * //adapted by miguel.galdon@uclm.es
 */
package concurrent_assignment3-Viktitors.A2;


import java.util.concurrent.Semaphore;

/**
 * You can only park if there is at least 1 free slot.
 * You can only leave if there is there is at least 1 parked car.
 * Create and use counters, and use signals as appropiate.
 * @author Pablo.Bermejo@uclm.es
 */

class CarParkControl {

    protected int spaces;
    protected int capacity;
    NumberCanvas cont_;
    Counter counter;
    int emptyslots;
    int cars;

    CarParkControl(int n) {
        capacity = spaces = n;
        cars=0;
        emptyslots=this.capacity;
    }
    
    CarParkControl(int n,NumberCanvas cont) {
        cont_ = cont;
        capacity = spaces = n;
        counter = new Counter(cont_);
        counter.show(spaces);
        cars=0;
        emptyslots=this.capacity;
    }

    synchronized void arrive() throws InterruptedException {
        while(emptyslots==0)wait();
        --spaces;
        counter.show(spaces);
        cars++;
        emptyslots--;
        notify();
    }

    synchronized void leaves() throws InterruptedException{
        
        while(cars==0)wait();
        ++spaces;
        counter.show(spaces);
        emptyslots++;
        cars--;
        notify();
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
