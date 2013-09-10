package com.eastflag.gameframework.scene;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface IScene {
	void update();
	void present(Canvas canvas);
	void onTouchEvent(MotionEvent event);
}