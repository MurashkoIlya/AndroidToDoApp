package by.scar.todoapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import by.scar.todoapp.R;
import by.scar.todoapp.model.ToDo;
import by.scar.todoapp.ui.MainActivity;
import by.scar.todoapp.ui.fragments.base.BaseFragment;
import by.scar.todoapp.util.ObjectBoxHelper;

public class ItemDetalizationFragment extends BaseFragment {

    private static final String TAG = ItemDetalizationFragment.class.getName();

    public static final String ARG_ID = TAG + "ARG_ID";
    private ToDo todo;

    private FloatingActionButton mDeleteButton;

    private TextView mTitle;
    private TextView mBody;
    private TextView isDone;

    private OnDeleteItemCallback callback;

    public static ItemDetalizationFragment newInstance(long id) {
        ItemDetalizationFragment fragment = new ItemDetalizationFragment();

        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detalization, container, false);
        initViews(view);

        todo = ObjectBoxHelper.getToDo(getArguments().getLong(ARG_ID));

        setViews(todo, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        callback = null;
    }

    private void initViews(View view){
        mTitle = view.findViewById(R.id.title_item_detalization);
        mBody = view.findViewById(R.id.body_item_detalization);
        isDone = view.findViewById(R.id.item_is_done_detalization);
        mDeleteButton = view.findViewById(R.id.item_delete_action);
    }

    private void setViews(ToDo todo, View view){
        mTitle.setText(todo.getTitle());
        mBody.setText(todo.getBody());

        if(todo.isDone()){
            isDone.setTextColor(view.getContext().getResources().getColor(R.color.itemDone));
            isDone.setText(view.getContext().getResources().getText(R.string.todo_done_txt));
        } else if(!todo.isDone()){
            isDone.setText(view.getContext().getResources().getText(R.string.todo_in_progress_txt));
            isDone.setTextColor(view.getContext().getResources().getColor(R.color.itemInProgress));
        }

        mDeleteButton.setOnClickListener(v-> {
            ObjectBoxHelper.popToDo(todo);
            callback = (MainActivity) getActivity();
            callback.onDeleteItem();
        });

    }

    public interface OnDeleteItemCallback{
        void onDeleteItem();
    }
}
