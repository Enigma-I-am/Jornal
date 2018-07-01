package okechukwu.nwagba.ng.com.jornal.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import okechukwu.nwagba.ng.com.jornal.Database.JornalEntity;
import okechukwu.nwagba.ng.com.jornal.Database.JornalRoomDataBase;

public class AddEntryViewModel extends ViewModel {

    private LiveData<JornalEntity> mEntry;

    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public AddEntryViewModel(JornalRoomDataBase dataBase, int id) {

        mEntry = dataBase.jornalDao().getSingleEntries(id);
    }



    // COMPLETED (7) Create a getter for the task variable
    public LiveData<JornalEntity> getTask() {
        return mEntry;
    }
}
