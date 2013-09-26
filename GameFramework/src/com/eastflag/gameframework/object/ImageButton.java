package com.eastflag.gameframework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class ImageButton extends Sprite {

	private Bitmap mBitmap, mBitmapOn;
	
	public ImageButton(Bitmap bitmap, Bitmap bitmapOn) {
		mBitmap = bitmap;
		mBitmapOn = bitmapOn;
	}
	
	//setPositon
	public void setPostion(int x, int y) {
		mX = x;
		mY = y;
	}
	
	public void setPosition(int centerX, int centerY, int width, int height) {
		super.setPosition(centerX, centerY, width, height);
		
		mBitmap =  Bitmap.createScaledBitmap(mBitmap, width, height, false);
	}
	
	//Bitmap 변경
	public void toggleButton() {
		
	}
	
	//present
	public void present(Canvas canvas) {
		canvas.drawBitmap(mBitmap, mX, mY, null);
	}
}