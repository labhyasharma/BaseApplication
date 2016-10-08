package in.editsoft.baseapplication.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.editsoft.baseapplication.R;
import in.editsoft.baseapplication.common.listener.DrawerListener;
import in.editsoft.baseapplication.common.listener.FragmentEventListener;
import in.editsoft.baseapplication.util.UIHelper;

public abstract class BaseActivity extends AppCompatActivity implements FragmentEventListener{

    private static final String KEY_FRAGMENT_CONTAINER_IDS = "frg_container_list_key";

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    protected List<Integer> containerList = new ArrayList<>();
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerListener mDrawerListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") ViewGroup rootView = (ViewGroup) layoutInflater.inflate(R.layout.activity_base, null);

        //add child layout to main container
        layoutInflater.inflate(getLayoutResourceId(), (ViewGroup) rootView.findViewById(R.id.main_container));
        setContentView(rootView);

        initToolbar();
        initTabLayout();

        UIHelper.setUpforKeyboard(rootView, this);

        if (savedInstanceState == null) {
            create(null);
        }
    }

    private void initDrawer() {
        //dataList = new ArrayList<DrawerItem>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);

        View headerview = navigationView.getHeaderView(0);

        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.nav_head);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                //drawer.closeDrawer(GravityCompat.START);
                mDrawerLayout.closeDrawers();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){


                }
                return true;
            }
        });
        //  mNavDrawerLayout = (LinearLayout) findViewById(R.id.nav_drawer);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open,
                R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (mDrawerListener != null) {
                    mDrawerListener.onDrawerClosed();
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (mDrawerListener != null) {
                    mDrawerListener.onDrawerOpened();
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if (!showDrawer()) {
            lockDrawer();
            return;
        }

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        BaseFragment drawerFragment = getDrawerFragment();

        if (showDrawer() && drawerFragment == null) {
            throw new NullPointerException("Drawer fragment should not be null");
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String tag = drawerFragment.getClass().getName();
        // fragmentTransaction.replace(R.id.nav_container, drawerFragment, tag);
        fragmentTransaction.commit();

    }

    public void lockDrawer() {
        if (mDrawerLayout == null) return;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(false);
    }

    public void unlockDrawer() {
        if (mDrawerLayout == null) return;
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
    }


    protected boolean showDrawer() {
        return false;
    }


    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        setToolbarTitle(getResources().getString(R.string.app_name));
    }

    protected  void initTabLayout(){
        if(isTabLayoutRequired()){
            setTabView();
        }else{
            findViewById(R.id.tab_layout).setVisibility(View.GONE);
        }
    }


    public BaseFragment getDrawerFragment() {
        return null;
    }
    protected boolean isTabLayoutRequired(){
        return false;
    }

    protected void setTabView(){
        throw new ExceptionInInitializerError("tabs not initialised");
    }

    protected abstract
    @LayoutRes
    int getLayoutResourceId();
    protected abstract void create(Bundle savedInstanceState);

    @Override
    public void addFragment(BaseFragment fragment, boolean addToBackStack) {

    }

    @Override
    public void addFragment(BaseFragment fragment, int containerId, boolean addToBackStack) {

    }

    @Override
    public void addFragment(BaseFragment fragment, boolean addToBackStack, int enterAnimation, int exitAnimation) {

    }

    @Override
    public void addFragment(BaseFragment fragment, boolean addToBackStack, int enterAnimation, int exitAnimation, int popEnterAnim, int popExitAnim) {

    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean addToBackStack) {

    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean addToBackStack, String tag) {

    }

    @Override
    public void replaceFragment(BaseFragment fragment, int containerId, boolean addToBackStack) {

    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean addToBackStack, int enterAnimation, int exitAnimation) {

    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean addToBackStack, int enterAnimation, int exitAnimation, int popEnterAnim, int popExitAnim) {

    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void setToolbarTitle(String title) {

        if (title == null) {
            title = "";
        }

        if (mToolbar != null) {
            TextView toolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setText(title);
        }

    }

    @Override
    public void setToolbarTitle(String name, @ColorRes int color) {

        if (mToolbar != null) {
            TextView toolbarTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
            toolbarTitle.setTextColor(UIHelper.getColor(this, color));
        }
    }

    @Override
    public void onRetryClicked() {

    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean addToBackStack, String tag, int enterAnimation, int exitAnimation) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(KEY_FRAGMENT_CONTAINER_IDS, (ArrayList<Integer>) containerList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        containerList = savedInstanceState.getIntegerArrayList(KEY_FRAGMENT_CONTAINER_IDS);
        if (containerList == null) {
            containerList = new ArrayList<>();
        }
    }

    protected final void addContainer(int containerId) {
        containerList.add(containerId);
    }
}
