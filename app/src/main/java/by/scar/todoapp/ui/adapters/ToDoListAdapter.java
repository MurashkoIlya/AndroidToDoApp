package by.scar.todoapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import by.scar.todoapp.R;
import by.scar.todoapp.model.ToDo;

//TODO:Complete Adapter and ToDoViewHolder

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoViewHolder> {
    private Context mContext;
    private List<ToDo> mToDoList;
    private OnItemClickListener mOnItemClickListener;

    public ToDoListAdapter(Context context, List<ToDo> toDoList, OnItemClickListener onItemClickListener){
        mContext = context;
        mToDoList = new ArrayList<>();
        mToDoList.addAll(toDoList);
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        final ToDo todo = mToDoList.get(position);

        holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(todo));

        holder.onBind(todo);
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView body;
        private TextView isDone;

        private ToDo mTodo;

        ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            body = itemView.findViewById(R.id.item_body);
            isDone = itemView.findViewById(R.id.item_is_done);
        }

        void onBind(ToDo todo){
            mTodo = todo;

            title.setText(mTodo.getTitle());
            body.setText(mTodo.getBody());
            if(mTodo.isDone()){
                isDone.setTextColor(mContext.getResources().getColor(R.color.itemDone));
                isDone.setText(mContext.getResources().getText(R.string.todo_done_txt));
            } else if(!mTodo.isDone()){
                isDone.setText(mContext.getResources().getText(R.string.todo_in_progress_txt));
                isDone.setTextColor(mContext.getResources().getColor(R.color.itemInProgress));
            }

            itemView.setOnClickListener(v-> mOnItemClickListener.onItemClick(todo));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ToDo todo);
    }
}
