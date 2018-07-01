package okechukwu.nwagba.ng.com.jornal.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import okechukwu.nwagba.ng.com.jornal.Database.JornalEntity;
import okechukwu.nwagba.ng.com.jornal.Repository.Repository;

public class JornalViewModel extends AndroidViewModel {
    private Repository mJornalRepository;
    private LiveData<List<JornalEntity>> mAllJornalEntities;


    public JornalViewModel(@NonNull Application application) {
        super(application);
        mJornalRepository = new Repository(application);
        mAllJornalEntities = mJornalRepository.getmEntity();

    }

    public LiveData<List<JornalEntity>> getmAllJornalEntities() {
        return mAllJornalEntities;
    }

    public void insertToJornal(JornalEntity entity){
        mJornalRepository.insert(entity);
    }


}
