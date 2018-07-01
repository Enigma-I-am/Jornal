package okechukwu.nwagba.ng.com.jornal.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.jar.JarEntry;

import okechukwu.nwagba.ng.com.jornal.DAO.JornalDao;
import okechukwu.nwagba.ng.com.jornal.Database.JornalEntity;
import okechukwu.nwagba.ng.com.jornal.Database.JornalRoomDataBase;

public class Repository {

    private JornalDao mDao;
    private LiveData<List<JornalEntity>> mEntity;


    public Repository(Application application) {
        JornalRoomDataBase DB = JornalRoomDataBase.getDatabase(application);
        mDao = DB.jornalDao();
        mEntity = mDao.getAllEntries();
    }


    public LiveData<List<JornalEntity>> getmEntity() {
        return mEntity;
    }

    public void insert(JornalEntity mJornalEntity) {
            new insertAsyncTask(mDao).execute(mJornalEntity);
    }


    private static class insertAsyncTask extends AsyncTask<JornalEntity, Void, Void> {

        private JornalDao AsyncTaskDao;

        public insertAsyncTask(JornalDao mAsyncTaskDao) {
            AsyncTaskDao = mAsyncTaskDao;
        }

        @Override
        protected Void doInBackground(final JornalEntity... jornalEntities) {
            AsyncTaskDao.insert(jornalEntities[0]);
            return null;
        }
    }
}
