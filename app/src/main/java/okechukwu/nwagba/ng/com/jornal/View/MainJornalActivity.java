package okechukwu.nwagba.ng.com.jornal.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import okechukwu.nwagba.ng.com.jornal.AddEntry;
import okechukwu.nwagba.ng.com.jornal.Database.JornalEntity;
import okechukwu.nwagba.ng.com.jornal.R;

public class MainJornalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jornal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainJornalActivity.this, AddEntry.class);
                startActivity(i);

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
}

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private List<JornalEntity> entities;

    public RecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = inflater.inflate(R.layout.entry_item, parent, false);
        return new RecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        if (entities != null) {
           JornalEntity current = entities.get(position);
            holder.textview.setText(current.getEntry());
        } else {
            // Covers the case of data not being ready yet.
            holder.textview.setText("");
        }

    }

    void setJornal(List<JornalEntity> mentities ){
        entities = mentities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (entities != null)
            return entities.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textview;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.info_text);
            cardView = itemView.findViewById(R.id.card_view);
        }


    }
}
