package by.scar.todoapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import by.scar.todoapp.R;
import by.scar.todoapp.model.ToDo;
import by.scar.todoapp.ui.MainActivity;
import by.scar.todoapp.ui.fragments.base.BaseFragment;
import by.scar.todoapp.util.ObjectBoxHelper;

public class AddItemFragment extends BaseFragment {
    private AppCompatEditText title;
    private AppCompatEditText body;
    private AppCompatButton addItemBtn;
    private OnCreateItemCallback callback;

    public static AddItemFragment newInstance() {

        Bundle args = new Bundle();

        AddItemFragment fragment = new AddItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        findViews(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        addItemBtn.setOnClickListener(v->{
            if(isValid()){

                ObjectBoxHelper.putToDo(new ToDo(0,
                        title.getText().toString(),
                        body.getText().toString(),
                        false));

                callback = (MainActivity) getActivity();
                callback.onCreateItem();
            }else Toast.makeText(getContext(), "Поля пусты!", Toast.LENGTH_SHORT).show();
        });

    }

    private void findViews(View view){
        title = view.findViewById(R.id.add_item_title);
        body = view.findViewById(R.id.add_item_body);
        addItemBtn = view.findViewById(R.id.add_item_button);
    }

    private boolean isValid(){
        if(title.getText().toString().trim().equals("") ||
                body.getText().toString().trim().equals(""))
            return false;
        else return true;
    }

    public interface OnCreateItemCallback{
        void onCreateItem();
    }
}
