package ru.technopark.startenglish.wordsUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.technopark.startenglish.R;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private final WordDataSource dataSource;

    public WordAdapter(String moduleName) {
        dataSource = WordDataSource.getInstance(moduleName);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.word_model, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String name = dataSource.getList().get(position).getName();
        holder.getName().setText(name);
    }

    @Override
    public int getItemCount() {
        return dataSource.getList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
