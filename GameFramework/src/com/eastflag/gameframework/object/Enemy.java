package com.eastflag.gameframework.object;

import android.graphics.Bitmap;

public class Enemy extends SpriteAnimation{
	private long localTime;
	private long localMissileTime;
	public boolean makeMissile;
	
	public Enemy(Bitmap bitmap) {
		super(bitmap);
	}
	
	public void update(){
		super.update();
		
		localTime += mAppDirector.getmDeltaTime();
		//10ms에 1px 움직이기
		while(localTime >= 10) {
			mY += 1;
			//화면에 안보일때 dead 처리
			if(mY > 1920 || mX<-mWidth || mX > 1080) {
				mIsDead = true;
				break;
			}
			localTime -=10;
			dstRect.set(mX, mY, mX+mWidth, mY+mHeight);
		}
		
		//3초마다 미사일 생성
		localMissileTime += mAppDirector.getmDeltaTime();
		while(localMissileTime >= 3000) {
			makeMissile = true;
			localMissileTime -= 3000;
		}
	}
}
