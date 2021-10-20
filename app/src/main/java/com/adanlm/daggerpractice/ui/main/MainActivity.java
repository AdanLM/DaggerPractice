package com.adanlm.daggerpractice.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.adanlm.daggerpractice.BaseActivity;
import com.adanlm.daggerpractice.R;
import com.adanlm.daggerpractice.ui.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                sesionManager.logOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void testFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ProfileFragment())
                .commit();
    }
}