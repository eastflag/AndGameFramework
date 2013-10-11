package com.eastflag.gameframework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class ImageButton extends Sprite {

	public boolean isClicked;
	private Bitmap mBitmapOn, mBitmapOff;
	private Rect srcRect;
	
	public ImageButton(Bitmap bitmapOn, Bitmap bitmapOff) {
		mBitmapOn = bitmapOn;
		mBitmapOff = bitmapOff;
		
		srcRect = new Rect(0, 0, mBitmapOn.getWidth(), mBitmapOn.getHeight());
	}
	
	//present
	public void present(Canvas canvas) {
		if(isClicked) {
			canvas.drawBitmap(mBitmapOff, srcRect, dstRect, null);
		} else {
			canvas.drawBitmap(mBitmapOn, srcRect, dstRect, null);
		}
	}
}
