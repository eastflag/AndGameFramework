package com.eastflag.gameframework.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.eastflag.gameframework.AppDirector;

//게임시작화면에서 좌측상단에 게임시작과 동시에 시간을 표시
// 표시방법 00:00
public class Timer {
	//property
	//그려질 x,y
	private int x, y;
	//토탈시간
	private long totalTime;
	
	private Paint mPaint;
	
	public Timer(int x, int y){
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setTextSize(100);
		mPaint.setAntiAlias(true);
		//프라퍼티 초기화
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		totalTime += AppDirector.getInstance().getmDeltaTime();
	}
	
	public void present(Canvas canvas) {
		canvas.drawText(String.valueOf(totalTime/1000), x, y, mPaint);
	}
	
}
