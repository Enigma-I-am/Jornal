package okechukwu.nwagba.ng.com.jornal.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
@Entity(tableName = "jornal_enrty_table")
public class JornalEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "jornal")
    private String mEntry;

    @Ignore
    public JornalEntity(@NonNull int id, @NonNull String mEntry) {
        this.id = id;
        this.mEntry = mEntry;
    }

    public JornalEntity(@NonNull String mEntry) {
        this.mEntry = mEntry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntry(){
        return this.mEntry;
    }
}
