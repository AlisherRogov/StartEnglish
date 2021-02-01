package ru.technopark.startenglish.wordsUI;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.technopark.startenglish.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {
    final private TextView name;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.word_definition_name);
    }

    public TextView getName() {
        return name;
    }
}
