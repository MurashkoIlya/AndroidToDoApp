package by.scar.todoapp.util;

import java.util.ArrayList;
import java.util.List;

import by.scar.todoapp.ToDoApp;
import by.scar.todoapp.model.ToDo;
import by.scar.todoapp.model.ToDo_;
import io.objectbox.Box;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;

public class ObjectBoxHelper {

    private static Box getToDoBox(){
        return ToDoApp.getInstance().getBoxStore().boxFor(ToDo.class);
    }

    public static List<ToDo> getToDos(){
        Query<ToDo> toDoQuery = getToDoBox()
                .query()
                .order(ToDo_.id, QueryBuilder.DESCENDING)
                .build();
        if (toDoQuery.count() > 0)
            return  toDoQuery.find();
        else return new ArrayList<>();
    }

    public static void putToDo(ToDo entity){
        getToDoBox().put(entity);
    }
}
