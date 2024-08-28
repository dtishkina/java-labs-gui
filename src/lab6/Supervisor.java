package lab6;

public class Supervisor extends Thread{
    private final AbstractProgram program;

    public Supervisor(AbstractProgram program) {
        this.program = program;
    }

    public void setStop() {
        program.interrupt();
        System.out.println("Supervisor: finish program");
    }

    public void setRunning() {
        program.setCondition(Condition.RUNNING);
        System.out.println("Supervisor: reload... RUNNING");
    }

    @Override
    public void run() {
        program.start();
        while (!program.isInterrupted()) {
            synchronized (program) {
                try{
                    program.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                switch (program.getCondition()) {
                    case STOPPING -> setRunning();
                    case FATAL_ERROR -> setStop();
                }
            }
        }
    }
}
