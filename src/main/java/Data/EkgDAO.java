package Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface EkgDAO {
    EkgDTO createEKG(int person_id, Date date);

    List<EkgDTO> load(Timestamp time);

}
