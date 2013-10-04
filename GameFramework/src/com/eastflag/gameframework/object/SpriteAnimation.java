package com.eastflag.gameframework.object;

import com.eastflag.gameframework.AppDirector;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteAnimation extends Sprite {
	private Bitmap mBitmap;
	private Rect srcRect; //비트맵상의 위치
	protected Rect dstRect; //화면상에 그려질 위치
	
	private int frameCount; // 프레임 갯수
	private int frameTime; // 한 프레임이 보여지는 시간
	private int currentFrame; //현재 보여지고 있는 프레임 인덱스(0부터 시작)
	private int width, height; //한 프레임의 넓이, 높이
	private int isRepeat; //1:반복, 2: 반복안함, 계속 그림, 3: 반복안함, 삭제
	private long localTime;
	
	//생성자에서는 bitmap만 초기화
	public SpriteAnimation(Bitmap bitmap) {
		mBitmap = bitmap;
		srcRect = new Rect(0,0,0,0);
		dstRect = new Rect(0,0,0,0);
	}
	
	public void setPosition(int centerX, int centerY, int width, int height) {
		super.setPosition(centerX, centerY, width, height);
		dstRect.set(mX, mY, mX+mWidth, mY+mHeight);
	}
	
	//init 함수에서 스프라이트 초기화
	public void init(int frameCount, int frameTime, int width, int height, int  isRepeat) {
		this.frameCount = frameCount;
		this.frameTime = frameTime;
		this.width = width;
		this.height = height;
		this.isRepeat = isRepeat;
	}
	
	public void update() {
		localTime += AppDirector.getInstance().getmDeltaTime();
		
		while(localTime>=frameTime) {
			currentFrame += 1;
			localTime -= frameTime;
			if(currentFrame>=frameCount) {
				//currentFrame = 0;
				if(isRepeat == 1) {
					currentFrame = 0;
				} else if(isRepeat ==2) {
					currentFrame -= 1; 
				} else {
					mIsDead = true;
				}
			}
		}
		
		//srcRect 세팅
		srcRect.left = currentFrame * width;
		srcRect.top = 0;
		srcRect.right = (currentFrame+1) * width;
		srcRect.bottom = height;
	}
	
	public void present(Canvas canvas) {
		canvas.drawBitmap(mBitmap, srcRect, dstRect, null);
	}
}
