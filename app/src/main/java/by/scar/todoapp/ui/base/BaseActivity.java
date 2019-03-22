package by.scar.todoapp.ui.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import by.scar.todoapp.R;
import by.scar.todoapp.ui.fragments.base.BaseFragment;

//TODO:Fragments work
public abstract class BaseActivity extends AppCompatActivity {

    public void replaceFragmentInMainFrame(BaseFragment fragment, boolean addToBackStack){
        replaceFragment(R.id.mainFrame, fragment, addToBackStack);
    }

    private void replaceFragment(int containerId, Fragment fragment, boolean addToStack){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .replace(containerId,fragment);
        if(addToStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

}
