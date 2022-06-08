package Data;

import Business.EkgObserver;

public class DummyEkgRecorder implements EkgDataRecorder {
    private EkgObserver observer;

    @Override
    public void record() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    double time = 0;
                    //Dummy data generation
                    while(true) {
                        Thread.sleep(50);
                        if (observer != null) {
                            observer.handle(new EkgDataImpl(70*Math.random(), time));
                            time+=0.5;
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
