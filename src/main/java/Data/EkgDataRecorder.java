package Data;

import Business.EkgObserver;
// Denne klasse genbrugt fra IT2-projektet
public interface EkgDataRecorder {
    void record();
    void setObserver(EkgObserver observer);
}
