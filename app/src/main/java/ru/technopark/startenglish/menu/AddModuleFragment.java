package ru.technopark.startenglish.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.technopark.startenglish.R;
import ru.technopark.startenglish.module.ModuleViewModel;

public class AddModuleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_module, container, false);
        ModuleViewModel mvm = new ViewModelProvider(this).get(ModuleViewModel.class);
        EditText editTextModule = v.findViewById(R.id.edit_text_module);

        Button addModuleButton = v.findViewById(R.id.add_module_button);

        addModuleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvm.createModule(editTextModule.getText().toString());
            }
        });

        return v;
    }
}
