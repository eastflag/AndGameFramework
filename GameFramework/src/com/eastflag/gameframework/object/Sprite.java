package com.eastflag.gameframework.object;

import android.graphics.Rect;

public abstract class Sprite {
	protected int mX, mY; //화면상에 비트맵이 그려질 위치
	protected int mWidth, mHeight; //화면상에 비트맵의 넓이, 높이
	protected Rect dstRect;
	
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
}
