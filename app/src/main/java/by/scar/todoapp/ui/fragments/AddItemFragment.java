package by.scar.todoapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import by.scar.todoapp.R;
import by.scar.todoapp.model.ToDo;
import by.scar.todoapp.ui.MainActivity;
import by.scar.todoapp.ui.fragments.base.BaseFragment;
import by.scar.todoapp.util.ObjectBoxHelper;

public class AddItemFragment extends BaseFragment {
    private static final String TAG = AddItemFragment.class.getName();

    public static final String ARG_ID = TAG + "ARG_ID";

    private AppCompatTextView newItemText;
    private AppCompatEditText title;
    private AppCompatEditText body;
    private AppCompatButton addItemBtn;
    private OnCreateItemCallback callback;

    public static AddItemFragment newInstance(@Nullable long id) {

        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);

        AddItemFragment fragment = new AddItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        findViews(view);

        long id = editAction();

        addItemBtn.setOnClickListener(v->{
            if(isValid()){

                ObjectBoxHelper.putToDo(new ToDo(id,
                        title.getText().toString(),
                        body.getText().toString(),
                        false));

                callback = (MainActivity) getActivity();
                callback.onCreateItem();
            }else Toast.makeText(getContext(), "Поля пусты!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        callback = null;
    }

    private void findViews(View view){
        title = view.findViewById(R.id.add_item_title_field);
        body = view.findViewById(R.id.add_item_body_field);
        addItemBtn = view.findViewById(R.id.add_item_button);
        newItemText = view.findViewById(R.id.new_item_txt);
    }

    private long editAction(){
        long id = getArguments().getLong(ARG_ID);
        if( id != 0){
            newItemText.setText("Edit item");
            ToDo entity = ObjectBoxHelper.getToDo(id);
            title.setText(entity.getTitle());
            body.setText(entity.getBody());
            addItemBtn.setText("Confirm");
            return id;
        }else return 0;
    }

    private boolean isValid(){
        if(title.getText().toString().equals("") ||
                body.getText().toString().equals(""))
            return false;
        else return true;
    }

    public interface OnCreateItemCallback{
        void onCreateItem();
    }
}
