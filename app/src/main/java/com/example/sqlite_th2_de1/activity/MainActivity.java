package com.example.sqlite_th2_de1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.sqlite_th2_de1.R;
import com.example.sqlite_th2_de1.model.Work;
import com.example.sqlite_th2_de1.database.WorkDAO;
import com.example.sqlite_th2_de1.adapter.ViewPageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;
    private WorkDAO mWorkDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWorkDAO = new WorkDAO(this);
        Work.setLastId(mWorkDAO.getMaxId());

        navigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.viewPager);
        floatingActionButton = findViewById(R.id.floating_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this, WorkAddActivity.class);
                startActivity(t);
            }
        });


        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.mList).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.mInfor).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.mFind).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mList:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.mInfor:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.mFind:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }
}