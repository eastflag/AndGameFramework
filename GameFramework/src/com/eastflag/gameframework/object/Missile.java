package com.eastflag.gameframework.object;

import android.graphics.Bitmap;

public class Missile extends SpriteObject{
	private long localTime;
	private int speed;

	public Missile(Bitmap bitmap, int speed) {
		super(bitmap);
		this.speed = speed;
	}
	
	//10ms에 3px 위로 이동
	//화면을 벗어나면 상태 프래그 세팅
	public void update() {
		localTime += mAppDirector.getmDeltaTime();
		
		while(localTime >= 10) {
			mY += speed;
			if(mY + mHeight < 0) {
				mIsDead = true;
				break;
			}
			localTime -= 10;
			dstRect.set(mX, mY, mX+mWidth, mY+mHeight);
		}
	}

}
