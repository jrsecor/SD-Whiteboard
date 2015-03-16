package com.hisham.edureka.drawingbrush;

import android.app.Activity;
import android.os.Bundle;

public class DrawingBrush extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyTouchEventView tv = new MyTouchEventView(this);
		setContentView(tv);
		addContentView(tv.btnReset, tv.params);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
