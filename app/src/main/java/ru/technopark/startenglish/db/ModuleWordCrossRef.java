package ru.technopark.startenglish.db;

import androidx.room.Entity;

@Entity(tableName = "module_word_cross_ref", primaryKeys = {"moduleId", "wordId"})
public class ModuleWordCrossRef {
    private long moduleId;
    private long wordId;

    public ModuleWordCrossRef(long moduleId, long wordId) {
        this.moduleId = moduleId;
        this.wordId = wordId;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public long getWordId() {
        return wordId;
    }

    public void setWordId(long wordId) {
        this.wordId = wordId;
    }

}
