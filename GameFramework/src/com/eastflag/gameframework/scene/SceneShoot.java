package com.eastflag.gameframework.scene;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.eastflag.gameframework.AppDirector;
import com.eastflag.gameframework.object.Background;
import com.eastflag.gameframework.object.Missile;
import com.eastflag.gameframework.object.Player;
import com.eastflag.gameframework.object.SpriteAnimation;
import com.eastflag.gameframework.object.SpriteObject;
import com.eastflag.gameframework.object.Timer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class SceneShoot implements IScene{
//	private Timer mTimer;
	
//	private Paint mPaint;
	private AppDirector mAppDirector;
	private Background mBack, mBackCloud;
	private Player mPlayer;
	private SpriteObject upKeypad, rightKeypad, downKeypad, leftKeypad, tapKeypad;
	
	private BlockingQueue<Missile> mMissileList = new ArrayBlockingQueue<Missile>(100);
	
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
		tapKeypad = new SpriteObject(mAppDirector.circle);
		tapKeypad.setPosition(150, 1770, 100, 100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
//		mTimer.update();
		mBack.update();
		mBackCloud.update();
		
		mPlayer.update();
		
		for(Missile missile : mMissileList) {
			missile.update();
			if(missile.getMIsDead()) {
				mMissileList.remove(missile);
			}
		}
		
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
		tapKeypad.present(canvas);
		
		for(Missile missile : mMissileList) {
			missile.present(canvas);
		}
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		//키패드 제어 : 해당 키가 클릭되었는지를 체크하여 제어
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(upKeypad.isSelected(event) == MotionEvent.ACTION_DOWN) {
				Log.d("ldk", "upKeypad is clicked");
				mPlayer.startMoving(0, -1);
			}
			if(rightKeypad.isSelected(event) == MotionEvent.ACTION_DOWN) {
				mPlayer.startMoving(1, 0);
			}
			if(downKeypad.isSelected(event) == MotionEvent.ACTION_DOWN) {
				mPlayer.startMoving(0, 1);
			}
			if(leftKeypad.isSelected(event) == MotionEvent.ACTION_DOWN) {
				mPlayer.startMoving(-1, 0);
			}
			if(tapKeypad.isSelected(event) == MotionEvent.ACTION_DOWN) {
				Missile missile = new Missile(mAppDirector.missile);
				missile.setPosition(mPlayer.getmX() + mPlayer.getmWidth()/2, mPlayer.getmY(), 45, 45);
				mMissileList.add(missile);
			}
			break;
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_CANCEL: //FALL-THROUGH
		case MotionEvent.ACTION_UP:
			mPlayer.stopMoving();
			break;
		}
	}

}
