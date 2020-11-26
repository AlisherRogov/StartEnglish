package ru.technopark.startenglish.words;

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
    }

    public TextView getName() {
        return name;
    }
}
