package com.eastflag.gameframework.object;

import com.eastflag.gameframework.AppDirector;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public abstract class Sprite {
	protected int mX, mY; //화면상에 비트맵이 그려질 위치
	protected int mWidth, mHeight; //화면상에 비트맵의 넓이, 높이
	protected Rect dstRect; //화면상에 그려지는 영역
	protected AppDirector mAppDirector;
	protected boolean mIsDead;
	
	public Sprite() {
		dstRect = new Rect(mX, mY, mX+mWidth, mY+mHeight);//인스턴스 생성
		mAppDirector = AppDirector.getInstance();
	}
	
	public void setPosition(int centerX, int centerY, int width, int height) {
		mX = centerX - width/2;
		mY = centerY - height/2;
		mWidth = width;
		mHeight = height;
		dstRect.set(mX, mY, mX+mWidth, mY+mHeight);
	}
	
	//버튼 체크 유무 
	public int isSelected(MotionEvent event) {
		int action = event.getAction();
		int result = -1;
//		if (event.getX() > mX && event.getX() < mX + mWidth && 
//				event.getY() > mY && event.getY() < mY + mHeight) {
		if(dstRect.contains((int)(event.getX()), (int)(event.getY())))  {
			Log.d("ldk", "Sprite is Selected");
			switch(action & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				result = MotionEvent.ACTION_DOWN;
				break;
			case MotionEvent.ACTION_MOVE:
				result = MotionEvent.ACTION_MOVE;
				break;
			case MotionEvent.ACTION_UP: //FALL_THROUGH
			case MotionEvent.ACTION_CANCEL:
				result = MotionEvent.ACTION_UP;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				result = MotionEvent.ACTION_POINTER_DOWN;
				break;
			}
		} 
		return result;
	}

	public int getmX() {
		return mX;
	}

	public int getmY() {
		return mY;
	}

	public int getmWidth() {
		return mWidth;
	}
	
	public int getmHeight() {
		return mHeight;
	}

	public Rect getDstRect() {
		return dstRect;
	}

	public boolean ismIsDead() {
		return mIsDead;
	}

	public void setmIsDead(boolean mIsDead) {
		this.mIsDead = mIsDead;
	}
}
