package Data;

import java.sql.Date;

public interface EkgSensorData {
    void setVoltage(double voltage);
    double getVoltage();
    void setTime(Long time);
    Long getTime();
}