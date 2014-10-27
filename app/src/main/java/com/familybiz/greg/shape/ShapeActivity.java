package com.familybiz.greg.shape;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;


public class ShapeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);

	    SeekBar shapeSideCount = (SeekBar)findViewById(R.id.shape_side_count_slider);
	    shapeSideCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
		    @Override
		    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
			    ShapeView shapeView = (ShapeView) findViewById(R.id.shape);
			    shapeView.setSideCount(seekBar.getProgress());
		    }

		    @Override
		    public void onStartTrackingTouch(SeekBar seekBar) {
		    }

		    @Override
		    public void onStopTrackingTouch(SeekBar seekBar) {
		    }
	    });
    }
}
