package Data;

import java.sql.Date;
import java.sql.Timestamp;
// DTO : Data Transfer Object. DAO: Data Access Object.
public class EkgDTO {
    private int id;
    private int person_id;
    private Date start_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setVoltage(double voltage) {
    }

    public void setTime(Timestamp time) {
    }
}
