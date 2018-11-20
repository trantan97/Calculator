package com.trantan.calculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private CalculatorFragment mCalculatorFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCalculatorFragment = (CalculatorFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_calculator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                mCalculatorFragment.clearTextResult();
                return true;
            case R.id.menu_save_result:
                mCalculatorFragment.saveResult();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
