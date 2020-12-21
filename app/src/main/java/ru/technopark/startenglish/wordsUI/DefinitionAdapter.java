package ru.technopark.startenglish.wordsUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.technopark.startenglish.R;
import ru.technopark.startenglish.word.Definition;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionViewHolder> {
    private final WordCardDataSource wordCardDataSource;

    public DefinitionAdapter(List<Definition> definitions) {
        wordCardDataSource = WordCardDataSource.getInstance(definitions);
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.definition_model, parent, false);
        return new DefinitionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        String name = wordCardDataSource.getList().get(position).toString();
        holder.getName().setText(name);
    }

    @Override
    public int getItemCount() {
        return wordCardDataSource.getList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
