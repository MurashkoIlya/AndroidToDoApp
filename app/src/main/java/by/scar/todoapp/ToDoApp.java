package by.scar.todoapp;

import android.app.Application;

import by.scar.todoapp.model.MyObjectBox;
import io.objectbox.BoxStore;

public class ToDoApp extends Application {
    private static ToDoApp instance;
    private static BoxStore boxStore;

    public ToDoApp(){
        super();
        instance = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();

    }

    public static ToDoApp getInstance(){
        if (instance == null) {
            instance = new ToDoApp();
        }
        return instance;
    }

    public static BoxStore getBoxStore() {
        return boxStore;
    }
}
