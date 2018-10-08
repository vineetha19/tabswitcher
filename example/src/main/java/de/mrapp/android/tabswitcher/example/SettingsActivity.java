
package de.mrapp.android.tabswitcher.example;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG = MainActivity.class.getName() + "::FragmentTag";

    private void initializeToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public final void setTheme(final int resid) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String themeKey = getString(R.string.theme_preference_key);
        String themeDefaultValue = getString(R.string.theme_preference_default_value);
        int theme = Integer.valueOf(sharedPreferences.getString(themeKey, themeDefaultValue));

        if (theme != 0) {
            super.setTheme(R.style.AppTheme_Dark);
        } else {
            super.setTheme(R.style.AppTheme);
        }
    }

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initializeToolbar();

        if (getFragmentManager().findFragmentByTag(FRAGMENT_TAG) == null) {
            Fragment fragment = Fragment.instantiate(this, PreferenceFragment.class.getName());
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, fragment, FRAGMENT_TAG);
            transaction.commit();
        }
    }

}