package com.example.orderfoodapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.orderfoodapp.fragment.HistoryFragment;
import com.example.orderfoodapp.fragment.HomeFragment;
import com.example.orderfoodapp.fragment.OrderFragment;
import com.example.orderfoodapp.fragment.ReportFragment;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_ORDER = 1;
    public static final int FRAGMENT_REPORT = 2;
    public static final int FRAGMENT_HISTORY = 3;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
     private int currentFragment = FRAGMENT_HOME;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.menu_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frContent, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if(currentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                toolbar.setTitle("Home");
                currentFragment = FRAGMENT_HOME;
            }

        } else if (id == R.id.nav_baocao) {
            if(currentFragment != FRAGMENT_ORDER){
                replaceFragment(new OrderFragment());
                toolbar.setTitle("Báo cáo");
                currentFragment = FRAGMENT_ORDER;
            }
        } else if (id == R.id.nav_hoadon) {
            if(currentFragment != FRAGMENT_REPORT){
                replaceFragment(new ReportFragment());
                toolbar.setTitle("Hóa đơn");
                currentFragment = FRAGMENT_REPORT;
            }
        } else if (id == R.id.nav_lichsu) {
            if(currentFragment != FRAGMENT_HISTORY){
                replaceFragment(new HistoryFragment());
                toolbar.setTitle("Lịch sử");
                currentFragment = FRAGMENT_HISTORY;
            }
        }

        // Đóng menu sau khi chọn mục
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}