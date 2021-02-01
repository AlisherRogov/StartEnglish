package ru.technopark.startenglish.wordsUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.technopark.startenglish.R;
import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.word.Word;

public class WordAdapter extends RecyclerView.Adapter<WordViewHolder> {
    private final WordDataSource dataSource;

    public WordAdapter(List<Word> words) {
        dataSource = WordDataSource.getInstance(words);
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
        String name = dataSource.getList().get(position).getWord();
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
