package Business;

import Data.EkgData;
import Data.EkgDataImpl;

public interface EkgObserver {
    void handle(EkgData ekgData);
}
