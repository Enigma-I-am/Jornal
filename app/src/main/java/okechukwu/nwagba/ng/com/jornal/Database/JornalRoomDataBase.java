package okechukwu.nwagba.ng.com.jornal.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import okechukwu.nwagba.ng.com.jornal.DAO.JornalDao;

@Database(entities = {JornalEntity.class}, version = 1)
public abstract class JornalRoomDataBase extends RoomDatabase {

    public abstract JornalDao jornalDao();

    private static JornalRoomDataBase INSTANCE;

    public static JornalRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = (JornalRoomDataBase) Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "jornal_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
