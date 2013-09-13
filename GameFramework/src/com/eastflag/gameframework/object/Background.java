package com.eastflag.gameframework.object;

import com.eastflag.gameframework.AppDirector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
	
	private Bitmap mBitmap;
	private Rect srcRect, dstRect;
	private long mLocalTime;
	private int mSpeedY; //y축으로 움직이는 속도
    private int mY;
	
	public Background(Bitmap bitmap) {
		mBitmap = bitmap;
		srcRect = new Rect(0, 0, 320, 600);
		dstRect = new Rect(0, 0, AppDirector.getInstance().mVirtualWidth,
				AppDirector.getInstance().mVirtualHeight);
	}
	
	public void setSpeed(int y) {
		mSpeedY = y;
	}
	
	public void update() {
		mLocalTime += AppDirector.getInstance().getmDeltaTime();
		//0.1초당 1px 아래로 움직이기
		while(mLocalTime>100) {  //mLocalTime이 200ms보다 클 경우 한번에 처리
			if(mLocalTime > 100) {
				mY += mSpeedY;
				srcRect.set(0, 0+mY, 320, 600+mY);
				mLocalTime -= 100;
				//한계치가 넘을때 초기화
				if(mY+600>2000){
					mY = 0;
				} else if(mY<0) {
					mY = 2000-600;
				}
			}
		}
	}
	
	public void present(Canvas canvas){
		canvas.drawBitmap(mBitmap, srcRect, dstRect, null);
	}
}
