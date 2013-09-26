package com.eastflag.gameframework.scene;

import com.eastflag.gameframework.AppDirector;
import com.eastflag.gameframework.object.Background;
import com.eastflag.gameframework.object.Player;
import com.eastflag.gameframework.object.SpriteAnimation;
import com.eastflag.gameframework.object.SpriteObject;
import com.eastflag.gameframework.object.Timer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class SceneShoot implements IScene{
//	private Timer mTimer;
	
//	private Paint mPaint;
	private AppDirector mAppDirector;
	private Background mBack, mBackCloud;
	private Player mPlayer;
	private SpriteObject upKeypad, rightKeypad, downKeypad, leftKeypad, tapKeypad;
	
	public SceneShoot() {
		mAppDirector = AppDirector.getInstance();
		//페인트 색 정의
//		mPaint = new Paint();
//		mPaint.setTextSize(30);
//		mPaint.setColor(Color.WHITE);
//		mTimer = new Timer(200, 200);
		
		//백그라운드
		mBack = new Background(mAppDirector.backGround);
		mBack.setSpeed(1);
		mBackCloud = new Background(mAppDirector.backCloud);
		mBackCloud.setSpeed(-1);
		
		mPlayer = new Player(AppDirector.getInstance().player);
		mPlayer.init(6, 100, 62, 104, true);
		mPlayer.setPosition(mAppDirector.mVirtualWidth/2, 
				mAppDirector.mVirtualHeight-200, 120, 200);
		
		upKeypad = new SpriteObject(mAppDirector.upTriangle);
		upKeypad.setPosition(150, 1670, 100, 100);
		rightKeypad = new SpriteObject(mAppDirector.rightTriangle);
		rightKeypad.setPosition(250, 1770, 100, 100);
		downKeypad = new SpriteObject(mAppDirector.downTriangle);
		downKeypad.setPosition(150, 1870, 100, 100);
		leftKeypad = new SpriteObject(mAppDirector.leftTriangle);
		leftKeypad.setPosition(50, 1770, 100, 100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
//		mTimer.update();
		mBack.update();
		mBackCloud.update();
		
		mPlayer.update();
	
	}

	@Override
	public void present(Canvas canvas) {
		//바탕색을 파란색으로 칠하기
//		canvas.drawColor(Color.BLUE);
		//회색 원 그리기
		//canvas.drawCircle(200, 200, 100, mPaint);
		//ToDo (숙제) : 100, 100에 deltaTime, 100, 200에 FPS 찍기
		//힌트 :  drawText 와 위에 선언된 mPaint 이용
//		long deltaTime = AppDirector.getInstance().getmDeltaTime();
//		try {
//			canvas.drawText("deltaTime: " + deltaTime, 100, 100, mPaint);
//			canvas.drawText("FPS: " + 1000f / deltaTime, 100, 200, mPaint);
//		} catch (ArithmeticException e) {
//
//		} catch (Exception e) {
//
//		}
		mBack.present(canvas);
		mBackCloud.present(canvas);

//		mTimer.present(canvas);
		mPlayer.present(canvas);
		
		upKeypad.present(canvas);
		rightKeypad.present(canvas);
		downKeypad.present(canvas);
		leftKeypad.present(canvas);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

}