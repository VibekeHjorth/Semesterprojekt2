package Data;

public class EkgSensorDataImpl implements EkgSensorData {
    private double voltage;
    private Long time;

    public EkgSensorDataImpl() {
    }

    public EkgSensorDataImpl(double voltage, Long time) {
        this.time=time;
        this.voltage=voltage;

    }

    @Override
    public double getVoltage() {
        return voltage;
    }

    @Override
    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    @Override
    public Long getTime() {
        return time;
    }

    @Override
    public void setTime(Long time) {
        this.time = time;
    }
}
