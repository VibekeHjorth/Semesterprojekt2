package Data;

import Business.EkgObserver;
// Denne klasse er genbrugt fra IT2-projektet
public class DummyEkgRecorder implements EkgDataRecorder {
    private EkgObserver observer;

    @Override
    public void record() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Long time = 0L;
                    //Dummy data generation
                    while(true) {
                        Thread.sleep(50);
                        if (observer != null) {
                            observer.handle(new EkgSensorDataImpl(70*Math.random(), time));
                            time+=1;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    @Override
    public void setObserver(EkgObserver observer) {
        this.observer=observer;
    }
}
