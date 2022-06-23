package Data;

import java.util.LinkedList;
import java.util.List;
/** @ Vibeke og @Asbjørn*/
public class DataConsumer implements Runnable {
    private static final int MAX_SIZE = 1500;
    private final LinkedList<EkgValues> dataList = new LinkedList<>();
    private final EkgDataAccess ekgDAO = new EkgDataAccess();
    private final Object emptyLock = new Object();
    private int currentEkgId;

    public int getValuesCount(){
        return dataList.size();
    }

    public void enqueue(EkgValues data){
        synchronized (dataList){
            // In case buffer is overrun, we just drop data -
            // This is instead of Pausing the producer if the queue is full. (fullLock)
            if (dataList.size()<MAX_SIZE) {
                dataList.add(data);
            }
            else {
                System.out.println("List error"); //fejl
            }
        }
    }

    public void waitOnEmpty() throws InterruptedException {
        synchronized (emptyLock){
            emptyLock.wait();
        }
    }
    public void notifyOnEmpty(){         // bliver informeret om at låsen bliver unlocked.
        synchronized (emptyLock){
            emptyLock.notifyAll();
        }
    }


    @Override
    public void run() {   //venter på der er et signal, hvis der er et signal, så sender den data til databasen.
        while(true){          //
            //if (dataList.isEmpty()){
            try {
                //This makes the Thread pause until the producer wakes it up
                waitOnEmpty();   //vent på listen er tom
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
            //}
            List<EkgValues> listCopy;
            synchronized (dataList){
                //Take a copy of list and empty it;
                listCopy = new LinkedList<>();
                listCopy.addAll(dataList);
                dataList.clear();

            }
            ekgDAO.createEkgValues(listCopy);
        }
    }
}
