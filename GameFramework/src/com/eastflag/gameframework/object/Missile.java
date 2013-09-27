package com.eastflag.gameframework.object;

import android.graphics.Bitmap;

public class Missile extends SpriteObject{
	private long localTime;
	private boolean mIsDead;

	public Missile(Bitmap bitmap) {
		super(bitmap);
	}
	
	//10ms에 3px 위로 이동
	//화면을 벗어나면 상태 프래그 세팅
	public void update() {
		localTime += mAppDirector.getmDeltaTime();
		
		while(localTime >= 10) {
			mY += -3;
			if(mY + mHeight < 0) {
				mIsDead = true;
				break;
			}
			localTime -= 10;
			dstRect.set(mX, mY, mX+mWidth, mY+mHeight);
		}
	}
	
	public boolean getMIsDead() {
		return mIsDead;
	}

}
