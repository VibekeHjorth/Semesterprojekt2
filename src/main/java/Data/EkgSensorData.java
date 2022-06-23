package Data;

import java.sql.Date;
/** @Vibeke */
public interface EkgSensorData {
    void setVoltage(double voltage);
    double getVoltage();
    void setTime(Long time);
    Long getTime();
}