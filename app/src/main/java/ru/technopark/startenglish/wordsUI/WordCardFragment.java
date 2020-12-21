package ru.technopark.startenglish.wordsUI;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.technopark.startenglish.MainActivity;
import ru.technopark.startenglish.R;
import ru.technopark.startenglish.word.Definition;
import ru.technopark.startenglish.word.WordViewModel;

public class WordCardFragment extends Fragment {
    private final static String WORDNAME = "wordName";
    private DefinitionAdapter definitionAdapter;
    @NonNull
    private String wordName = "default";
    @NonNull
    private String definition = "Incorrect word";

    private final static String DEFINITION = "definition";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            definition = savedInstanceState.getString(DEFINITION, "Incorrect word");
        }

        if (getArguments() != null)
            wordName = getArguments().getString(WORDNAME, "default");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.word_card, container, false);
        TextView tv = v.findViewById(R.id.word_card_text);
        tv.setText(wordName);

        RecyclerView recyclerView = v.findViewById(R.id.word_card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        WordViewModel wvm = new ViewModelProvider(this).get(WordViewModel.class);
        wvm.getWord(wordName);

        wvm.lastWord.observe(getViewLifecycleOwner(), word -> {
            if (word != null) {
                if (!word.getWord().equals("Empty Word") && word.getDefinitions() != null) {
//                    wvm.saveWord();

                    System.out.println("QWERTY__________________________");
                    for (Definition d : word.getDefinitions()) {
                        System.out.println(d.getDefinition());
                    }
                    System.out.println("QWERTY__________________________");

                    definitionAdapter = new DefinitionAdapter(word.getDefinitions());
                    recyclerView.setAdapter(definitionAdapter);
                }/* else {
                    definitionAdapter = new DefinitionAdapter(new ArrayList<>());
                }*/
            }

//            if (word == null) {
//                System.out.println("NULL in observe lambda");
//            } else {
//                if (!word.getWord().equals("Empty Word")) {
////                    wvm.saveWord();
//                    definitionAdapter = new DefinitionAdapter(word.getDefinitions());
//                } else {
//                    definitionAdapter = new DefinitionAdapter(new ArrayList<>());
//                }
//            }
//            recyclerView.setAdapter(definitionAdapter);
        });



        recyclerView.setAdapter(definitionAdapter);

/*        WordViewModel wvm = new ViewModelProvider(this).get(WordViewModel.class);
        wvm.getWord(wordName);

        wvm.lastWord.observe(getViewLifecycleOwner(), word -> {
            if (word == null) {
                System.out.println("NULL in observe lambda");
            } else {
                if (!word.getWord().equals("Empty Word")) {
                    wvm.saveWord();
                    definition = word.getWord() + "\n" + word.getDefinitions();
                    tv.setText(definition);
                }
            }
        });*/

        final String[] audioUrl = new String[1];
        wvm.soundUrl.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.equals("")) {
                    audioUrl[0] = s;
//                    textView.setText(s);
                } else {
                    audioUrl[0] = "https://media.merriam-webster.com/audio/prons/en/us/mp3/c/cat00001.mp3";
                }
            }
        });

        final MediaPlayer[] mPlayer = new MediaPlayer[1];
        Button button2 = v.findViewById(R.id.sound_button);
        button2.setOnClickListener(view -> {
            button2.setEnabled(false);
            mPlayer[0] = new MediaPlayer();
            mPlayer[0].setAudioStreamType(AudioManager.STREAM_MUSIC);
            try{
                mPlayer[0].setDataSource(audioUrl[0]);

                mPlayer[0].prepare();

                mPlayer[0].start();
                Toast.makeText(getContext(),"Playing",Toast.LENGTH_SHORT).show();
            }catch (IOException | SecurityException | IllegalStateException | IllegalArgumentException e){
                e.printStackTrace();
            }

            mPlayer[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Toast.makeText(getContext(),"End",Toast.LENGTH_SHORT).show();
                    button2.setEnabled(true);
                }
            });

        });

        return v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DEFINITION, definition);
    }
}
