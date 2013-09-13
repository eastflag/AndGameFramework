package com.eastflag.gameframework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ImageButton {

	private int mX, mY; //비트맵이 그려질 위치
	private int centerX, centerY; //비트맵이 그려지는 중앙 지점
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
		mX = centerX - width/2;
		mY = centerY - height/2;
		
		mBitmap =  Bitmap.createScaledBitmap(mBitmap, width, height, false);
	}
	
	//update
	
	//버튼 체크 유무 
	public boolean isClicked(int x, int y) {
		if (x > mX && x < mX + mBitmap.getWidth() && y > mY
				&& y < mY + mBitmap.getWidth()) {
			return true;
		} else {
			return false;
		}
	}
	
	//Bitmap 변경
	public void toggleButton() {
		
	}
	
	//present
	public void present(Canvas canvas) {
		canvas.drawBitmap(mBitmap, mX, mY, null);
	}
}
