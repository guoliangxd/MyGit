package com.guoliang.dnote.model;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.guoliang.dnote.global.NoteFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class Note
 *
 * @author XhinLiang
 */
public class Note {

    private AVObject avObject;
    private String title;
    private String content;
    private Calendar calendar;
    private static final DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        calendar = Calendar.getInstance();
    }

    public Note(AVObject avObject) {
        this.avObject = avObject;
        this.title = avObject.getString("title");
        this.content = avObject.getString("content");
        Date date = avObject.getDate("createdAt");
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(date.getTime());
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private long getCreatedAtTimestamp() {
        return calendar.getTimeInMillis();
    }

    public void saveInBackground(SaveCallback callback) {
        // 如果这个为空，这条笔记没有同步过
        if (avObject == null) {
            this.avObject = new AVObject("Note");
            NoteFactory.getInstance().addNote(this);
        }
        this.avObject.put("title", this.getTitle());
        this.avObject.put("content", this.getContent());
        this.avObject.put("createAt", this.getCreatedAtTimestamp());
        this.avObject.put("owner", AVUser.getCurrentUser());
        this.avObject.saveInBackground(callback);
    }

    public void deleteInBackground(){
        this.avObject.deleteInBackground();
    }


    public String getCreatedAt() {
        return dateFormat.format(calendar.getTime());
    }
}
