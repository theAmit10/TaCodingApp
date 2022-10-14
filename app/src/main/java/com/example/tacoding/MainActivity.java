package com.example.tacoding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.tacoding.Fragment.ContestFragment;
import com.example.tacoding.Fragment.NewsFragment;
import com.example.tacoding.Fragment.ProblemFragment;
import com.example.tacoding.Fragment.ProfileFragment;
import com.example.tacoding.Fragment.UserFragment;
import com.example.tacoding.databinding.ActivityMainBinding;
import com.example.tacoding.tadatabase.PlatformViewModel;
import com.iammert.library.readablebottombar.ReadableBottomBar;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());



        // setting the root fragment for home page
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, new ContestFragment());
        transaction.commit();

        binding.bottomNavigation.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {

                // this is used to change one fragment to another.
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (i){
                    case 0:
                        transaction.replace(R.id.fragmentContainer, new ContestFragment());
                        break;
                    case 1:
//                        transaction.replace(R.id.fragmentContainer, new UserFragment());
                        transaction.replace(R.id.fragmentContainer, new ProblemFragment());
                        break;
                    case 2:
//                        transaction.replace(R.id.fragmentContainer, new NewsFragment());
                        transaction.replace(R.id.fragmentContainer, new ProfileFragment());
                        break;
//                    case 3:
//                        transaction.replace(R.id.fragmentContainer, new ProblemFragment());
//                        break;
//                    case 4:
//                        transaction.replace(R.id.fragmentContainer, new ProfileFragment());
//                        break;
                }

                transaction.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem = menu.findItem(R.id.searchAction);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("SEARCH HERE ");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }
}


