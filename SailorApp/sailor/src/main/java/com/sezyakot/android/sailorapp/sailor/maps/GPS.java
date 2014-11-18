package com.sezyakot.android.sailorapp.sailor.maps;

import android.os.Bundle;
import com.sezyakot.android.sailorapp.sailor.DefaultActivity;
import com.sezyakot.android.sailorapp.sailor.R;

/**
 * Created by Android on 18.11.2014.
 */
public class GPS extends DefaultActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setWrapper(true);
        getLayoutInflater().inflate(R.layout.gps, wrapper);
        setContentView(wrapper);
        setTitle(getString(R.string.gps));
        setCategoryTitle(true);
    }
}
