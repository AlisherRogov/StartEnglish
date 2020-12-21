package ru.technopark.startenglish.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SoundApi {

    class Entries {
        public List<Entry> entries;
    }

    class Entry {
        public Pronunciation hwi;

        @Override
        public String toString() {
            return "Entry{" +
                    "hwi=" + hwi +
                    '}';
        }
    }

    class Pronunciation {
        public List<Sound> prs;

        @Override
        public String toString() {
            return "Pronunciation{" +
                    "prs=" + prs +
                    '}';
        }
    }

    class Sound {
        public Audio sound;

        @Override
        public String toString() {
            return "Sound{" +
                    "sound=" + sound +
                    '}';
        }
    }

    class Audio {
        public String audio;

        @Override
        public String toString() {
            return "Audio{" +
                    "audio='" + audio + '\'' +
                    '}';
        }
    }

    @GET("{word}?key=6ab6b8e9-3f98-4931-bbd6-0f2e09b25ad9")
    Call<List<Entry>> get(@Path("word") String word);
}
