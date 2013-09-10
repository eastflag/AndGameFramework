package com.eastflag.gameframework.scene;

import com.eastflag.gameframework.AppDirector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class SceneStart implements IScene{
	
	private Paint mPaint;
	
	public SceneStart() {
		//페인트 색 정의
		mPaint = new Paint();
		mPaint.setTextSize(30);
		mPaint.setColor(Color.WHITE);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(Canvas canvas) {
		//바탕색을 파란색으로 칠하기
		canvas.drawColor(Color.BLUE);
		//회색 원 그리기
		//canvas.drawCircle(200, 200, 100, mPaint);
		//ToDo (숙제) : 100, 100에 deltaTime, 100, 200에 FPS 찍기
		//힌트 :  drawText 와 위에 선언된 mPaint 이용
		long deltaTime = AppDirector.getInstance().getmDeltaTime();
		try {
			canvas.drawText("deltaTime: " + deltaTime, 100, 100, mPaint);
			canvas.drawText("FPS: " + 1000f / deltaTime, 100, 200, mPaint);
		} catch (ArithmeticException e) {

		} catch (Exception e) {

		}
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

}
