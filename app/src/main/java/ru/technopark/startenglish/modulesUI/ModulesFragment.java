package ru.technopark.startenglish.modulesUI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.technopark.startenglish.R;
import ru.technopark.startenglish.module.Module;
import ru.technopark.startenglish.module.ModuleViewModel;
import ru.technopark.startenglish.word.Word;
import ru.technopark.startenglish.word.WordViewModel;

public class ModulesFragment extends Fragment {
    private ModuleAdapter moduleAdapter;
    static OnModuleSelectedListener onModuleSelectedListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ModuleViewModel wvm = new ViewModelProvider(this).get(ModuleViewModel.class);
//        wvm.createModule("first");
//        wvm.createModule("second");
//
//        wvm.getAllModules();

//        List<Module> modules = wvm.allModules.getValue();
    /*    System.out.println("___________QWERTY____________");

        System.out.println("QWETYERT_______________________DFGHJKL<MNB " + modules.size());

        for (Module m : modules) {
            System.out.println(m.getModuleName());
        }*/
//        wvm.allModules.observe(this, modules -> moduleAdapter = new ModuleAdapter(modules));

//        moduleAdapter = new ModuleAdapter(modules);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.module_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.module_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ModuleViewModel mvm = new ViewModelProvider(this).get(ModuleViewModel.class);
        WordViewModel wvm = new ViewModelProvider(this).get(WordViewModel.class);
        mvm.getAllModules();
//        mvm.createModule("first");
////        mvm.createModule("second");
//        mvm.getAllModules();
//
//        mvm.getModule("first");

//        System.out.println("Last module name = " + mvm.lastModule.getValue().getModuleName());

//        WordViewModel wvm = new ViewModelProvider(this).get(WordViewModel.class);
//        wvm.getWord("toy");
//        wvm.saveWord(mvm.lastModule.getValue());

/*        mvm.lastModule.observe(getViewLifecycleOwner(), module -> {
            if (module == null) {
                System.out.println("NULL in observe lambda");
            } else {
                if (module.getWords() != null) {
//                    module.getWords().add(wvm.lastWord.getValue());
                }
//                wvm.saveWord(module);

                System.out.println("Module name == " + module.getModuleName());

                System.out.println("__________________________________\n\n");
                for (Word word : module.getWords())
                    System.out.println(word.getWord());

                System.out.println("__________________________________\n\n");
            }
        });*/


        mvm.allModules.observe(getViewLifecycleOwner(), modules -> {
            if (modules != null) {
                moduleAdapter = new ModuleAdapter(modules);
                recyclerView.setAdapter(moduleAdapter);
            }

//            for (Module m : modules) {
//                mvm.getModule(m.getModuleName());
////                Module module = mvm.lastModule.getValue();
////                System.out.println("Module name ______________________" + m.getModuleName());
//                mvm.lastModule.observe(getViewLifecycleOwner(), new Observer<Module>() {
//                    @Override
//                    public void onChanged(Module module) {
//                        if (module.getModuleName().equals("first")) {
//                            wvm.getWord("idea");
//
//                            wvm.lastWord.observeForever(new Observer<Word>() {
//                                @Override
//                                public void onChanged(Word word) {
//                                    wvm.saveWord(module);
//                                    wvm.lastWord.removeObserver(this);
//                                }
//                            });
//
//                        }
//
//                        if (module.getModuleName().equals("second")) {
//                            wvm.getWord("idea");
//
//                            wvm.lastWord.observeForever(new Observer<Word>() {
//                                @Override
//                                public void onChanged(Word word) {
//                                    wvm.saveWord(module);
//                                    wvm.lastWord.removeObserver(this);
//                                }
//                            });
//
//                            wvm.getWord("game");
//
//                            wvm.lastWord.observeForever(new Observer<Word>() {
//                                @Override
//                                public void onChanged(Word word) {
//                                    wvm.saveWord(module);
//                                    wvm.lastWord.removeObserver(this);
//                                }
//                            });
//
//                        }
//                        if (module.getWords() != null) {
//                            for (Word w : module.getWords()) {
//                                System.out.println("Word from " + module.getModuleName() + "________" + w.getWord());
//                            }
//                        } else {
//                            System.out.println("________________________QWERTY_________________");
//                        }
//                    }
//                });
////                if (module.getWords() != null) {
////                    for (Word w : module.getWords()) {
////                        System.out.println("Word from " + module.getModuleName() + "________" + w.getWord());
////                    }
////                }
//            }
        });


//        recyclerView.setAdapter(moduleAdapter);
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
