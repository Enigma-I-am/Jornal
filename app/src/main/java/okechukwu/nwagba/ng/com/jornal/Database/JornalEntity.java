package okechukwu.nwagba.ng.com.jornal.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity(tableName = "jornal_enrty_table")
public class JornalEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "jornal")
    private String mEntry;

    public JornalEntity(String entry) {
        this.mEntry = entry;
    }

    public String getEntry(){
        return this.mEntry;
    }
}
