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
    @ColumnInfo(name = "Entry")
    private String mEntry;

    @NonNull
    public String mEntryTitle;



    @Ignore
    public JornalEntity(@NonNull int id, @NonNull String mEntry) {
        this.id = id;
        this.mEntry = mEntry;

    }

    public JornalEntity(int id,@NonNull String mEntry, @NonNull String mEntryTitle) {
        this.id = id;
        this.mEntry = mEntry;
        this.mEntryTitle = mEntryTitle;

    }

    @NonNull
    public String getmEntryTitle() {
        return mEntryTitle;
    }

    public void setmEntryTitle(@NonNull String mEntryTitle) {
        this.mEntryTitle = mEntryTitle;
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

    public void setmEntry(@NonNull String mEntry) {
        this.mEntry = mEntry;
    }






}
