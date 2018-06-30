package okechukwu.nwagba.ng.com.jornal.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final JornalDao mDao;

        PopulateDbAsync(JornalRoomDataBase db) {
            mDao = db.jornalDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            JornalEntity entity = new JornalEntity("Hello");
            mDao.insert(entity);
            JornalEntity mentity = new JornalEntity("World");
            mDao.insert(mentity);
            return null;
        }
    }
}
