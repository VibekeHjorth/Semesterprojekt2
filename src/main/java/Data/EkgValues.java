package Data;

import java.util.ArrayList;
import java.util.Date;

public class EkgValues {
    int id = 0;
    int ekg_Id =0 ;
    int voltage = 0;
    Date ekg_Time= null ;


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

    public int getVoltage(int voltage) {return voltage;};

    public void setVoltage(int voltage) {this.voltage=voltage;};

    public Date getEkg_Time(Date ekg_Time){return ekg_Time;};

    public void setEkg_Time(Date ekg_Time){this.ekg_Time=ekg_Time;};



}
