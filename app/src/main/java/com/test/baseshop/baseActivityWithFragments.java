package com.test.baseshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.test.baseshop.model_helper.DBHelper;


public class baseActivityWithFragments extends AppCompatActivity implements baseInterfaceMVP.View{


    private baseInterfaceMVP.Presenter main_presenter;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        prepareView();
        initFragmentManager();
        initPresenter();
//        initDBHelperInstance();
        setListenerToNavigationView();


        main_presenter.getStartPage();
    }

    private void prepareView(){
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        ImageView image_logo_in_actionbar = new ImageView(actionBar.getThemedContext());
        image_logo_in_actionbar.setScaleType(ImageView.ScaleType.FIT_XY);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                120,
                120,
                Gravity.CENTER
        );
        image_logo_in_actionbar.setLayoutParams(lp);
        image_logo_in_actionbar.setImageDrawable(getDrawable(R.drawable.icon_logo_2));
        actionBar.setCustomView(image_logo_in_actionbar);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    private void initPresenter(){
        main_presenter = new baseActivityWithFragments_Presenter(this);
    }

    private void initFragmentManager(){
        fm = this.getSupportFragmentManager();
    }


    @Deprecated
    private void initDBHelperInstance(){
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.copyDataBase();
        dbHelper.updateDataBase();
    }

    private void setListenerToNavigationView(){
        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        main_presenter.setIconBySexOfUser(this,bnv.getMenu().findItem(R.id.menu_main_profile));
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                main_presenter.OnItemClick(menuItem.getItemId());
                return false;
            }
        });
    }
//TODO:EXIT DEFAULT ICON
    @Override
    public void initDefaultSelectedMenuItem(int id){
        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.getMenu().setGroupCheckable(0,false,true);
        bnv.getMenu().setGroupCheckable(0,true,true);
        Log.d("INIT DEFAULT","!");
        bnv.getMenu().findItem(id).setChecked(true);
    }

    @Override
    public void initStartPage(Fragment fragment){
        showPage(fragment);
    }




    @Override
    public void showPage(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        if(!fm.popBackStackImmediate(fragment.getClass().getName(),0)) {
            ft.replace(R.id.current_page, fragment);
            ft.addToBackStack(fragment.getClass().getName());
            ft.commit();
        }
    }
}
