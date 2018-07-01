package okechukwu.nwagba.ng.com.jornal.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import okechukwu.nwagba.ng.com.jornal.Database.JornalEntity;
import okechukwu.nwagba.ng.com.jornal.R;
import okechukwu.nwagba.ng.com.jornal.ViewModel.JornalViewModel;

public class MainJornalActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener{

    private JornalViewModel mJornalViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jornal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,this);
        adapter.setClickListener(this);

        mJornalViewModel = ViewModelProviders.of(this).get(JornalViewModel.class);

        mJornalViewModel.getmAllJornalEntities().observe(this, new android.arch.lifecycle.Observer<List<JornalEntity>>() {
            @Override
            public void onChanged(@Nullable List<JornalEntity> jornalEntities) {
                adapter.setJornal(jornalEntities);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainJornalActivity.this, AddEntry.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            JornalEntity entity = new JornalEntity(requestCode,data.getStringExtra(AddEntry.EXTRA_REPLY),data.getStringExtra(AddEntry.EXTRA_REPLY_TITLE));
            mJornalViewModel.insertToJornal(entity);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "",
                    Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void itemClicked(View view, int position) {
        Intent intent = new Intent(MainJornalActivity.this, AddEntry.class);
        intent.putExtra(AddEntry.EXTRA_JORNALENTRY, position);
        intent.putExtra(AddEntry.EXTRA_JORNAL_ENTRY_TITLE,position);
        intent.putExtra(AddEntry.ENTRY_ID, position);
        startActivity(intent);
    }
}

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private final LayoutInflater inflater;
    private List<JornalEntity> entities;
    private Context mContext;
    private  ClickListener clicklistener = null;


    public RecyclerViewAdapter(MainJornalActivity mainJornalActivity, Context context) {
        mContext =context;
        inflater = LayoutInflater.from(context);

    }

    public void setClickListener(ClickListener clickListener){
        this.clicklistener = clickListener;
    }

    public interface ClickListener {
         void itemClicked(View view ,int position);
    }



    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.entry_item, parent, false);
        return new RecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        int BackGroundColor = getRandonColor(position);

        if (entities != null) {

            JornalEntity current = entities.get(position);
            holder.Entrytextview.setText(current.getEntry());
            holder.TitletextView.setText(current.mEntryTitle);
            holder.cardView.setCardBackgroundColor(BackGroundColor);


        } else {
            // Covers the case of data not being ready yet.
            holder.Entrytextview.setText("Saved");

        }

    }

    void setJornal(List<JornalEntity> mentities ){
        entities = mentities;
        notifyDataSetChanged();
    }

    private int getRandonColor(int priority) {

        if (priority > 7){
            priority = priority % 7;
        }

        switch (priority) {
            case 0:
                return ContextCompat.getColor(mContext,R.color.materialblue);

            case 1:
                return ContextCompat.getColor(mContext, R.color.materialblue);

            case 2:
                return ContextCompat.getColor(mContext, R.color.materialblueGray);

            case 3:
                return ContextCompat.getColor(mContext, R.color.materialgreen);

            case 4:
                return ContextCompat.getColor(mContext, R.color.materialpink);

            case 5:
                return ContextCompat.getColor(mContext, R.color.materialyellow);

            case 6:
                return ContextCompat.getColor(mContext, R.color.materialred);

            default:
                return ContextCompat.getColor(mContext,R.color.materialgreen);


        }

    }

    @Override
    public int getItemCount() {
        if (entities != null)
            return entities.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Entrytextview;
        TextView TitletextView;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            Entrytextview = itemView.findViewById(R.id.info_text);
            TitletextView = itemView.findViewById(R.id.summary);
            cardView = itemView.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(clicklistener !=null){
                        clicklistener.itemClicked(v,getAdapterPosition());
                    }
                }
            });
        }



    }
}
