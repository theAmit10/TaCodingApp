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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                        ExecutorService service = Executors.newSingleThreadExecutor();
                        service.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    transaction.replace(R.id.fragmentContainer, new ContestFragment());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
//                        transaction.replace(R.id.fragmentContainer, new ContestFragment());
                        break;
                    case 1:
                        ExecutorService service1 = Executors.newSingleThreadExecutor();
                        service1.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    transaction.replace(R.id.fragmentContainer, new ProblemFragment());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
//                        transaction.replace(R.id.fragmentContainer, new ProblemFragment());
                        break;
                    case 2:

                        ExecutorService service2 = Executors.newSingleThreadExecutor();
                        service2.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    transaction.replace(R.id.fragmentContainer, new ProfileFragment());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
//                        transaction.replace(R.id.fragmentContainer, new ProfileFragment());
                        break;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


