package com.eastflag.gameframework.object;

import android.graphics.Rect;
import android.view.MotionEvent;

public abstract class Sprite {
	protected int mX, mY; //화면상에 비트맵이 그려질 위치
	protected int mWidth, mHeight; //화면상에 비트맵의 넓이, 높이
	protected Rect dstRect; //화면상에 그려지는 영역
	protected boolean isOn; //현재 선택이 되었느냐
	
	public Sprite() {
		dstRect = new Rect(mX, mY, mX+mWidth, mY+mHeight);//인스턴스 생성
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
		int result = -1;
		if (event.getX() > mX && event.getX() < mX + mWidth && 
				event.getY() > mY && event.getY() < mY + mHeight) {
			switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				result = MotionEvent.ACTION_DOWN;
				isOn = true;
				break;
			case MotionEvent.ACTION_MOVE:
				result = MotionEvent.ACTION_MOVE;
				break;
			case MotionEvent.ACTION_UP: //FALL_THROUGH
			case MotionEvent.ACTION_CANCEL:
				result = MotionEvent.ACTION_UP;
				isOn = false;
				break;
			}
		} 
		return result;
	}
}
