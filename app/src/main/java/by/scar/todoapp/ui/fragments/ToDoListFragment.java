package by.scar.todoapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import by.scar.todoapp.R;
import by.scar.todoapp.model.ToDo;
import by.scar.todoapp.ui.MainActivity;
import by.scar.todoapp.ui.adapters.ToDoListAdapter;
import by.scar.todoapp.ui.fragments.base.BaseFragment;
import by.scar.todoapp.util.ObjectBoxHelper;

public class ToDoListFragment extends BaseFragment {
    private FloatingActionButton addBTn;
    private TextView ntsTxt;

    private OnAddItemClickCallback addItemClickCallback;

    private RecyclerView mRecycler;

    public static ToDoListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ToDoListFragment fragment = new ToDoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        findViews(view);

        mRecycler = view.findViewById(R.id.todo_list);
        mRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecycler.setAdapter(new ToDoListAdapter(initItems(view.getContext())));
        return view;
    }

    private List<ToDo> initItems(Context context){
        return ObjectBoxHelper.getToDos();
    }

    @Override
    public void onStart() {
        super.onStart();

        addBTn.setOnClickListener(v->{
            addItemClickCallback = (MainActivity) getActivity();
            addItemClickCallback.onAddClick();
        });
    }

    public interface OnAddItemClickCallback{
        void onAddClick();
    }

    public void findViews(View view){
        addBTn = view.findViewById(R.id.add_item_fab);
        ntsTxt = view.findViewById(R.id.ntsText);
    }
}
