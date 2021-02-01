package ru.technopark.startenglish.modulesUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.technopark.startenglish.R;
import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.module.ModuleViewModel;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleViewHolder> {
    private final ModuleDataSource dataSource;

    public ModuleAdapter(List<Module> modules) {
        dataSource = ModuleDataSource.getInstance(modules);
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
//        System.out.println("QWERT_______________" + dataSource.getList().get(position).getModuleName());
        String name = dataSource.getList().get(position).getModuleName();
//        int wordCount = dataSource.getList().get(position).getWords().size();
        int wordCount = 0;
        if (dataSource.getList().get(position).getSize() != 0)
            wordCount = dataSource.getList().get(position).getSize();
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
