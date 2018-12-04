package C2;

import java.util.concurrent.*;



public class CyclicBarrier {

    private Semaphore barrier = new Semaphore(0);
    private Semaphore mutex   = new Semaphore(1);
    private int group_size;
    private int arrived = 0;

    public CyclicBarrier(int group_size) {
	this.group_size = group_size;
    }

    public void await() throws InterruptedException {
        
	mutex.acquire();
        arrived++;
        mutex.release();
        if(arrived==group_size){
            barrier.release(group_size-1);          //more efficient way, all are blocked but the last one, when it arrives, all start moving
            arrived=0;           
        }
        else{
        barrier.acquire();      
        }
    }
}
