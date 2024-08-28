package lab6;

import java.util.Random;

public class AbstractProgram extends Thread{
    private volatile Condition cond;
    public AbstractProgram(){
        this.cond = Condition.UNKNOWN;
    }

    public void setCondition(Condition cond) {
        this.cond = cond;
    }

    public Condition generateCondition() {
        this.cond = Condition.values()[new Random().nextInt(Condition.values().length)];
        return cond;
    }

    public Condition getCondition(){
        return cond;
    }

    @Override
    public void run() {
        Thread daemon = new Thread(() -> {
            while (!this.isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    synchronized (this){
                        switch (this.generateCondition()) {
                            case UNKNOWN, RUNNING-> {
                                setCondition(Condition.RUNNING);
                                System.out.println("RUNNING");
                            }
                            case STOPPING -> {
                                System.out.println("STOPPING");
                                setCondition(Condition.STOPPING);
                            }
                            case FATAL_ERROR -> {
                                System.out.println("FATAL_ERROR");
                                setCondition(Condition.FATAL_ERROR);
                            }
                        }
                        this.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemon.setDaemon(true);
        daemon.start();
        System.out.println(this.getCondition());

//        while (!Thread.currentThread().isInterrupted()){
//            int a = 0;
//            a++;
//        }
    }
}
