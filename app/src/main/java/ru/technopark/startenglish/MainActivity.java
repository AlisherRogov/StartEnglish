package ru.technopark.startenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
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
        EditText enter = findViewById(R.id.enter);
        Button button = findViewById(R.id.search);
        button.setOnClickListener(view -> v.getWord(String.valueOf(enter.getText())));

        v.lastWord.observe(this, word -> {
            if (word.getWord().equals("")) {
                textView.setText("Empty word");
            } else {
                textView.setText(word.getWord() + "\n" + word.getDefinitions());
            }
        });
    }
}