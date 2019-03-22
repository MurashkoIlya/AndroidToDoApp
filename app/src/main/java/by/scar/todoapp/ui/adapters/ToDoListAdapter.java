package by.scar.todoapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import by.scar.todoapp.R;
import by.scar.todoapp.model.ToDo;

//TODO:Complete Adapter and ViewHolder

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {
    private List<ToDo> mToDoList;

    public ToDoListAdapter(List<ToDo> toDoList){
        mToDoList = new ArrayList<>();
        mToDoList.addAll(toDoList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ToDo todo = mToDoList.get(position);

        holder.body.setText(todo.getBody());
        holder.title.setText(todo.getTitle());

        holder.onBind(todo);
    }

    public void setmToDoList(List<ToDo> toDoList){
        mToDoList = toDoList;
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView body;
        private CheckBox isDone;

        private ToDo mTodo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.add_item_title);
            body = itemView.findViewById(R.id.add_item_body);
            isDone = itemView.findViewById(R.id.item_is_done);
        }

        void onBind(ToDo todo){
            mTodo = new ToDo(todo.getId(), todo.getTitle(), todo.getBody(), todo.isDone());
        }
    }
}
