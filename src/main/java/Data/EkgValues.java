package Data;

import java.util.ArrayList;
import java.util.Date;
/** @Vibeke og @Asbjørn*/
public class EkgValues {
    int id = 0;
    int ekg_Id =0 ;
    double voltage = 0; //Y
    Long ekg_Time= null ; //X

    public EkgValues() {
    }

    public EkgValues(int id, int ekg_Id, double voltage, Long ekg_Time) {
        this.id = id;
        this.ekg_Id = ekg_Id;
        this.voltage = voltage;
        this.ekg_Time = ekg_Time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEkg_Id() {
        return ekg_Id;
    }

    public void setEkg_id(int ekg_Id) {
        this.ekg_Id = ekg_Id;
    }

    public double getVoltage() {
        return voltage;
    };

    public void setVoltage(double voltage) {this.voltage=voltage;};

    public Long getEkg_Time(){
        return ekg_Time;};

    public void setEkg_Time(Long ekg_Time){this.ekg_Time=ekg_Time;};



}
