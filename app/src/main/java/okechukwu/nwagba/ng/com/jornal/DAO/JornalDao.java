package okechukwu.nwagba.ng.com.jornal.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import okechukwu.nwagba.ng.com.jornal.Database.JornalEntity;
@Dao
public interface JornalDao {

    @Insert
    void insert(JornalEntity jornal);

    @Query("DELETE FROM jornal_enrty_table")
    void deleteAll();

    @Query("SELECT * from jornal_enrty_table ORDER BY jornal ASC")
    LiveData<List<JornalEntity>> getAllEntries();

}
