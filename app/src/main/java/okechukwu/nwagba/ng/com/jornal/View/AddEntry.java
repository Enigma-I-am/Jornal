package okechukwu.nwagba.ng.com.jornal.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okechukwu.nwagba.ng.com.jornal.Database.JornalEntity;
import okechukwu.nwagba.ng.com.jornal.Database.JornalRoomDataBase;
import okechukwu.nwagba.ng.com.jornal.R;
import okechukwu.nwagba.ng.com.jornal.ViewModel.AddEntryViewModel;
import okechukwu.nwagba.ng.com.jornal.ViewModel.AddEntryViewModelFactory;

public class AddEntry extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY_TITLE = "com.example.android.wordlistsql.REPLY_TITLE";
    public static final String EXTRA_JORNALENTRY = "extraJornalentry";
    public static final String EXTRA_JORNAL_ENTRY_TITLE = "extrajornalentrytitlt";
    public static final String ENTRY_ID = "extraTaskId";

    public static final String INSTANCE_ENTRY_ID = "instanceTaskId";

    private static final int DEFAULT_ENTRY_ID = -1;

    private EditText editText;
    private EditText TitleEditText;

    private JornalRoomDataBase mDb;

    private int mTaskId = DEFAULT_ENTRY_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarentry);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mDb = JornalRoomDataBase.getDatabase(this);

        editText = findViewById(R.id.Edit_text);
        TitleEditText = findViewById(R.id.Title);
        final Button button = findViewById(R.id.button);

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_ENTRY_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_ENTRY_ID, DEFAULT_ENTRY_ID);
        }


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(ENTRY_ID)) {
            button.setText("Update");
            if (mTaskId == DEFAULT_ENTRY_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(ENTRY_ID, DEFAULT_ENTRY_ID);

                AddEntryViewModelFactory factory = new AddEntryViewModelFactory(mDb, mTaskId);

                final AddEntryViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(AddEntryViewModel.class);

                // COMPLETED (12) Observe the LiveData object in the ViewModel. Use it also when removing the observer
                viewModel.getTask().observe(this, new Observer<JornalEntity>() {
                    @Override
                    public void onChanged(@Nullable JornalEntity entity) {
                        if (entity != null) {
                            TitleEditText.setText(entity.mEntryTitle);
                            editText.setText(entity.getEntry());
                        }
                    }
                });
            }
        }





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editText.getText()) || TextUtils.isEmpty(TitleEditText.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {

                    String mEntry = editText.getText().toString();
                    String mEntryTitle =TitleEditText.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY_TITLE ,mEntryTitle);
                    replyIntent.putExtra(EXTRA_REPLY, mEntry);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });


    }
}
