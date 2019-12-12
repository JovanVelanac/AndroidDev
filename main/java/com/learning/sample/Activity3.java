package com.learning.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Activity3 extends AppCompatActivity {

    private String[] data = new String[]{
            "Some say the world will end in fire,",
            "Some say in ice.",
            "From what I’ve tasted of desire",
            "I hold with those who favor fire.",
            "But if it had to perish twice,",
            "I think I know enough of hate",
            "To say that for destruction ice",
            "Is also great,",
            "And would suffice."
    };

    private class DataHolder extends RecyclerView.ViewHolder {
        private TextView poemLine;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            poemLine = itemView.findViewById(R.id.dataText);
        }

        public void bind(String line) {
            poemLine.setText(line);


        }
    }

    private class DataAdapter extends RecyclerView.Adapter<DataHolder> {

        @NonNull
        @Override
        public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data, parent, false);
            return new DataHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull DataHolder holder, int position) {
            holder.bind(data[position]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }
    }

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        recyclerView = findViewById(R.id.recyclerView);
        dataAdapter = new DataAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dataAdapter);


    }
}
