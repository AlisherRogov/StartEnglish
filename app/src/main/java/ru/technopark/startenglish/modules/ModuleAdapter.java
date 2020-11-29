package ru.technopark.startenglish.modules;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.technopark.startenglish.R;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleViewHolder> {
    private final ModuleDataSource dataSource = ModuleDataSource.getInstance();

    public ModuleAdapter() {
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.module_model, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        String name = dataSource.getList().get(position).getName();
        int wordCount = dataSource.getList().get(position).getWordCount();
        holder.getName().setText(name);
        holder.getWordCount().setText(String.valueOf(wordCount));
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
