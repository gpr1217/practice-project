package com.practiceex.ui;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Pratyu on 12/3/2017.
 */

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Log.d("SelectedItem",item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("SelectedItem","Nothing");
    }
}
