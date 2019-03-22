package by.scar.todoapp.model;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ToDo {

    @Id
    private long id;
    private String title;
    private String body;
    private boolean isDone;

    public ToDo(long id, String title, String body, boolean isDone){
        this.id = id;
        this.title = title;
        this.body = body;
        this.isDone = isDone;
    }

    public ToDo(String title, String body, boolean isDone){
        this.title = title;
        this.body = body;
        this.isDone = isDone;
    }

    public ToDo(long id){
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public boolean isDone() {
        return isDone;
    }
}
