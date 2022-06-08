package Data;

import Business.EkgObserver;

public interface EkgDataRecorder {
    void record();
    void setObserver(EkgObserver observer);
}
