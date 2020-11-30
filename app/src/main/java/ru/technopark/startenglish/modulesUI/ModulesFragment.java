package ru.technopark.startenglish.modulesUI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.technopark.startenglish.R;

public class ModulesFragment extends Fragment {
    private ModuleAdapter moduleAdapter;
    static OnModuleSelectedListener onModuleSelectedListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moduleAdapter = new ModuleAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.module_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.module_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(moduleAdapter);
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onModuleSelectedListener = (OnModuleSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnModuleSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onModuleSelectedListener = null;
    }
}
