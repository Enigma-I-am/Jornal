package okechukwu.nwagba.ng.com.jornal.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import okechukwu.nwagba.ng.com.jornal.Database.JornalRoomDataBase;

public class AddEntryViewModelFactory  extends ViewModelProvider.NewInstanceFactory {

    private final JornalRoomDataBase mDb;
    private final int mTaskId;

    // COMPLETED (3) Initialize the member variables in the constructor with the parameters received
    public AddEntryViewModelFactory(JornalRoomDataBase database, int taskId) {
        mDb = database;
        mTaskId = taskId;
    }

    // COMPLETED (4) Uncomment the following method
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddEntryViewModel(mDb, mTaskId);
    }
}
