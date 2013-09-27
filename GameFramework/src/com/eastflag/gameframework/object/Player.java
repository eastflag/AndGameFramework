package com.eastflag.gameframework.object;

import com.eastflag.gameframework.AppDirector;

import android.graphics.Bitmap;

public class Player extends SpriteAnimation {
	
	private final int  PLAYER_VELOCITY = 10; //10ms 당 1px
	
	private boolean mIsMoving = false;
	private int directionX = 0;
	private int directionY = 0;
	private long localTime;

	public Player(Bitmap bitmap) {
		super(bitmap);
	}
	
	@Override
	public void update() {
		super.update();
		
		// 10ms당 1px 움직이기
		if (mIsMoving) {
			localTime += AppDirector.getInstance().getmDeltaTime();
			while (localTime >= PLAYER_VELOCITY) {
				mX += directionX;
				mY += directionY;
				if(mX<0) {
					mX = 0;
				}
				if(mX + mWidth > mAppDirector.mVirtualWidth) {
					mX = mAppDirector.mVirtualWidth - mWidth;
				}
				if(mY < 0) {
					mY = 0;
				}
				if(mY + mHeight > mAppDirector.mVirtualHeight) {
					mY = mAppDirector.mVirtualHeight - mHeight;
				}
				localTime -= PLAYER_VELOCITY;
				dstRect.set(mX, mY, mX+mWidth, mY+mHeight);
			}
		}
	}

	public void startMoving(int x, int y) {
		directionX = x;
		directionY = y;
		mIsMoving = true;
	}
	
	public void stopMoving() {
		mIsMoving = false;
	}
}
