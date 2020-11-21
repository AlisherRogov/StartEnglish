package ru.technopark.startenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.technopark.startenglish.network.ApiRepo;
import ru.technopark.startenglish.network.DictionaryApi;
import ru.technopark.startenglish.word.Word;
import ru.technopark.startenglish.word.WordViewModel;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WordViewModel v = new ViewModelProvider(this).get(WordViewModel.class);

        TextView textView = findViewById(R.id.word);

        DictionaryApi dictionaryApi = ApiRepo.from(this).getDictionaryApi();
        final String[] word = new String[1];
        Word w = v.getWord("owl");
//        Log.d("Donee", w.getWord());
        String tmp = w.getWord() +"\n"+ w.getPronunciation() + "\n";
        textView.setText(tmp);
        EditText enter = findViewById(R.id.enter);
    }

}