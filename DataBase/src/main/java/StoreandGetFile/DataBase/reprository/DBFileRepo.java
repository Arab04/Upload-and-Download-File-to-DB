package StoreandGetFile.DataBase.reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;import StoreandGetFile.DataBase.model.DBFileModel;

@Repository
public interface DBFileRepo extends JpaRepository<DBFileModel, String>{

}
