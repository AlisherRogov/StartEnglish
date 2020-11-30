package ru.technopark.startenglish.wordsUI;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.technopark.startenglish.R;

public class WordViewHolder extends RecyclerView.ViewHolder {
    final private TextView name;

    public WordViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.word_model_name);
        name.setOnClickListener(v -> {
            if (WordsFragment.onWordSelectedListener != null) {
                WordsFragment.onWordSelectedListener.onWordSelected(String.valueOf(name.getText()));
            }
        });
    }

    public TextView getName() {
        return name;
    }
}
