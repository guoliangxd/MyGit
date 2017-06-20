package com.guoliang.dnote.global;

import com.avos.avoscloud.AVObject;
import com.guoliang.dnote.model.Note;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by XhinLiang on 2017/5/6.
 *
 * @author XhinLiang
 */

public class NoteFactory {
    private static final NoteFactory ourInstance = new NoteFactory();

    private List<Note> notes;

    public static NoteFactory getInstance() {
        return ourInstance;
    }

    public void refresh(List<AVObject> list) {
        this.notes.clear();
        for (AVObject item : list) {
            Note note = new Note(item);
            this.notes.add(note);
        }
    }

    private NoteFactory() {
        notes = new LinkedList<>();
    }

    public void addTestNotes() {
        for (int i = 0; i < 30; ++i) {
            String content = "this is content.";
            for (int j = 0; j < 300; ++j) {
                content += " content " + i;
            }
            notes.add(new Note("title " + i, content));
        }
    }

    public void addNote(Note note) {
        notes.add(note);

    }

    public List<Note> getNotes() {
        return notes;
    }
}
