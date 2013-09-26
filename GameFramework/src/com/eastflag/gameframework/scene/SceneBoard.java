package com.eastflag.gameframework.scene;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class SceneBoard implements IScene {

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(Canvas canvas) {
		canvas.drawColor(Color.LTGRAY);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
