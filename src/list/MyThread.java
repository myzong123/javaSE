package list;

import javax.lang.model.element.VariableElement;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author myz
 * @create 2023/3/17-15:34
 */
public class MyThread implements Runnable{

    int count = 3;
    int money = 100;
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        try{
            if(count == 1){
                System.out.println(Thread.currentThread().getName() + "抢到了"+ money + "红包");
                Thread.yield();
            }else{
                System.out.println(Thread.currentThread().getName() + "没抢到红包");
            }
        }finally {
            lock.unlock();
        }
    }
}