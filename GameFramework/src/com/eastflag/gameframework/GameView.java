package com.eastflag.gameframework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

//SurfaceView 상속: 필름
//Callback 구현 : 필름의 상태가 바뀔때 호출되는 인터페이스. 3가지의 필름 상태를 정의
public class GameView extends SurfaceView implements Callback{
	RenderingThread mRenderingThread;
	Paint mPaint;

	public GameView(Context context) {
		super(context);
		
		//1. 필름과 연필(mHolder)을 만들어서 애니메이션 작가를 고용해서 넘겨줌
		SurfaceHolder mHolder = getHolder();
		mHolder.addCallback(this);
		mRenderingThread = new RenderingThread(this, mHolder);
		
		//페인트 색 정의
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
	}

	//필름을 카메라에 끼운 상태
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//2. 애니메이션 작가에게 일을 시켜야 됨.
		mRenderingThread.start();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	//5. 애니메이션 작가를 고용 해제(쓰레드 정지)
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mRenderingThread.setRunning(false);
	}
	
	//3. 필름의 상태 업데이트
	public void update() {
		
	}
	
	//4. 필름에 그림 그리기
	public void present(Canvas canvas){
		//바탕색을 파란색으로 칠하기
		canvas.drawColor(Color.BLUE);
		//회색 원 그리기
		//canvas.drawCircle(200, 200, 100, mPaint);
		//ToDo (숙제) : 100, 100에 deltaTime, 100, 200에 FPS 찍기
		//힌트 :  drawText 와 위에 선언된 mPaint 이용
	}

}
