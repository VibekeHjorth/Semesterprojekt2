package Data;

import java.sql.Date;
import java.sql.Timestamp;
// DTO : Data Transfer Object. DAO: Data Access Object.
public class EkgDTO {

    private int id;
    private int person_id;
    private Timestamp start_time;

    public EkgDTO() {
    }

    public EkgDTO(int id, int person_id, Timestamp start_time) {
        this.id = id;
        this.person_id = person_id;
        this.start_time = start_time;
    }



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

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }
}
