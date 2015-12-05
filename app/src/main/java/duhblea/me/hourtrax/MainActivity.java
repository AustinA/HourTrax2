package duhblea.me.hourtrax;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final short PROGRESS_STATUS = 0;
    private static final short WEEK_1 = 1;
    private static final short WEEK_2 = 2;

    private int currentFrag = 0;

    private Biweek biweek;
    private FragmentStack backStack;

    //Fragments
    private ProgressStatus progressStatus;
    private Week1 week1;
    private Week2 week2;

    private Toolbar toolbar;
    private TextView navHeader;

    private static boolean firstStart = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Toolbar setup and initialization
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (firstStart) {
            currentFrag = PROGRESS_STATUS;
            firstStart = false;
            toolbar.setTitle("HourTrax");
        }
        else {
            currentFrag = sharedPreferences.getInt("currentFrag", -1);
            switch (currentFrag) {
                case WEEK_1:
                    toolbar.setTitle("Week 1");
                    break;
                case WEEK_2:
                    toolbar.setTitle("Week 2");
                    break;
                default:
                    toolbar.setTitle("HourTrax");
                    break;
            }
        }


        setSupportActionBar(toolbar);

        //Drawer setup and initialization
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerClosed(drawerView);
                hideKeyboard();
            }

        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        //Naviation setup and inititalization
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initialize supporting Objects
        biweek = new Biweek(this);
        biweek.populateWeek1();
        biweek.populateWeek2();

        navHeader = (TextView) findViewById(R.id.navbarHours);
        updateNavHeader();

        backStack = new FragmentStack(3);

        //Fragment instantiation
        progressStatus = ProgressStatus.newInstance(this);
        week1 = Week1.newInstance(this);
        week2 = Week2.newInstance(this);


        switch (currentFrag) {
            case PROGRESS_STATUS:
                    getFragmentManager().beginTransaction().replace(R.id.current_fragment, progressStatus).commit();

                    break;
            case WEEK_1:
                    getFragmentManager().beginTransaction().replace(R.id.current_fragment, week1).commit();

                    break;
            case WEEK_2:
                    getFragmentManager().beginTransaction().replace(R.id.current_fragment, week2).commit();

                    break;
            default:
                    getFragmentManager().beginTransaction().replace(R.id.current_fragment, progressStatus).commit();

                    break;
            }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!backStack.isEmpty()) {
                if (backStack.peek() instanceof ProgressStatus) {
                    toolbar.setTitle("HourTrax");
                }
                else if (backStack.peek() instanceof Week1) {
                    toolbar.setTitle("Week 1");
                }
                else {
                    toolbar.setTitle("Week 2");
                }
                getFragmentManager().beginTransaction().replace(R.id.current_fragment, backStack.pull()).commit();
            }
            else {
                super.onBackPressed();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment currentFragment = getFragmentManager().findFragmentById(R.id.current_fragment);
        backStack.push(currentFragment);
        switch (id) {
            case R.id.week1_menu_item:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.current_fragment, week1)
                        .commit();
                toolbar.setTitle("Week 1");
		        currentFrag = WEEK_1;
                break;
            case R.id.week2_menu_item:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.current_fragment, week2)
                        .commit();
                toolbar.setTitle("Week 2");
		        currentFrag = WEEK_2;
                break;
            default:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.current_fragment, progressStatus)
                        .commit();
                toolbar.setTitle("HourTrax");
		        currentFrag = PROGRESS_STATUS;
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }


    public Biweek getBiweek() {
        return biweek;
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("currentFrag", currentFrag);
        editor.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences.Editor editor = prefs.edit();
        //editor.putInt("currentFrag", PROGRESS_STATUS);
        //editor.commit();

    }

    public void updateNavHeader() {
        navHeader.setText(Float.toString(biweek.calculateTotal()) + " hours worked this pay period");
    }


}
