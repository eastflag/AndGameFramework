package com.eastflag.gameframework.scene;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.eastflag.gameframework.AppDirector;
import com.eastflag.gameframework.object.Background;
import com.eastflag.gameframework.object.Enemy;
import com.eastflag.gameframework.object.Explosion;
import com.eastflag.gameframework.object.Missile;
import com.eastflag.gameframework.object.Player;
import com.eastflag.gameframework.object.Sprite;
import com.eastflag.gameframework.object.SpriteAnimation;
import com.eastflag.gameframework.object.SpriteObject;
import com.eastflag.gameframework.object.TextObject;
import com.eastflag.gameframework.object.Timer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewDebug.ExportedProperty;

public class SceneShoot implements IScene{
//	private Timer mTimer;
	private final int STATE_START = 1; //게임시작
	private final int STATE_OVER = 3;  //게임 오버
	private int mState = STATE_START;
	
	private int Time_To_Display_Enemy = 5000;
	
//	private Paint mPaint;
	private AppDirector mAppDirector;
	private Background mBack, mBackCloud;
	private Player mPlayer;
	private SpriteObject upKeypad, rightKeypad, downKeypad, leftKeypad, tapKeypad;
	
	private BlockingQueue<Missile> mMissileList = new ArrayBlockingQueue<Missile>(100);
	private BlockingQueue<Enemy> mEnemyList = new ArrayBlockingQueue<Enemy>(100);
	private BlockingQueue<Missile> mEnemyMissileList = new ArrayBlockingQueue<Missile>(100);
	private BlockingQueue<Explosion> mExplosionList = new ArrayBlockingQueue<Explosion>(100); 
	
	private long localTime;
	
	private TextObject mGameOver;
	
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
		mPlayer.init(6, 100, 62, 104, 1);
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
		
		mGameOver = new TextObject("Game Over", Color.YELLOW, 200);
		mGameOver.setPosition(1080/2, 1980/2, 0, 0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
//		mTimer.update();
		localTime += mAppDirector.getmDeltaTime();
		
		mBack.update();
		mBackCloud.update();
		
		if(mState == STATE_START) 
			mPlayer.update();
		
		//아군 미사일
		for(Missile missile : mMissileList) {
			missile.update();
			if(missile.ismIsDead()) {
				mMissileList.remove(missile);
			}
		}
		
		//적군 리스트
		for(Enemy enemy : mEnemyList) {
			enemy.update();
			if(enemy.ismIsDead()) {
				mEnemyList.remove(enemy);
				continue;
			}
			if(enemy.makeMissile) {
				//미사일 생성
				Missile missile = new Missile(mAppDirector.enemy_missile, 3); //10ms에 3px 아래로
				missile.setPosition(enemy.getmX()+enemy.getmWidth()/2, 
						enemy.getmY()+enemy.getmHeight(), 45, 45);
				mEnemyMissileList.add(missile);
				enemy.makeMissile = false;
			}
		}
		
		//적군 미사일 리스트
		for(Missile missile : mEnemyMissileList) {
			missile.update();
			if(missile.ismIsDead()) {
				mEnemyMissileList.remove(missile);
			}
		}
		
		for(Explosion explosion : mExplosionList) {
			explosion.update();
			if(explosion.ismIsDead()) {
				mExplosionList.remove(explosion);
			}
		}
		
		addEnemy();
		checkCollision();
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
		
		for(Enemy enemy : mEnemyList) {
			enemy.present(canvas);
		}
		
		//적군 미사일 리스트
		for(Missile missile : mEnemyMissileList) {
			missile.present(canvas);
		}
		
		for(Explosion explosion : mExplosionList) {
			explosion.present(canvas);
		}
		
		if(mState == STATE_OVER) {
			mGameOver.present(canvas);
		}
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		if(mState == STATE_OVER) 
			return;
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
				Missile missile = new Missile(mAppDirector.missile, -3); //10ms에 위로 3px
				missile.setPosition(mPlayer.getmX() + mPlayer.getmWidth()/2, mPlayer.getmY(), 45, 45);
				mMissileList.add(missile);
				if(mAppDirector.getSound())
					mAppDirector.playSoundEffect(AppDirector.SOUND_MY_MISSILE);
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

	private void addEnemy() {
		while(localTime >= Time_To_Display_Enemy) {
			Random rand = new Random();
			
			//랜덤타입을 정해서 비트맵과 type을 넘겨준다.
			int type = rand.nextInt(3);
			
			Enemy enemy = new Enemy(mAppDirector.enemy[type], type);
			enemy.init(6, 100, 62, 104, 1);
			int startX = enemy.getmWidth()/2 + rand.nextInt(1080-enemy.getmWidth());
			int startY = -enemy.getmHeight();
			enemy.setPosition(startX, startY, 120, 200);
			mEnemyList.add(enemy);
			localTime -= Time_To_Display_Enemy;
			
			Time_To_Display_Enemy = (3 + rand.nextInt(5)) * 1000;  // 3초 ~ 7초
		}
	}
	
	private void checkCollision() {
		//아군 미사일과 적군 충돌체크
		for(Missile missile : mMissileList) {
			for(Enemy enemy : mEnemyList) {
				if(checkBoxToBox(missile, enemy)) {
					//미사일과 적군을 remove
					//mMissileList.remove(missile);
					//mEnemyList.remove(enemy);
					missile.setmIsDead(true);
					enemy.setmIsDead(true);
					//폭발효과 처리
					addExplosion(enemy);
					break;
				}
			}
		}
		
		//적군과 아군 충돌 체크
		for(Enemy enemy : mEnemyList) {
			if(checkBoxToBox(enemy, mPlayer)) {
				enemy.setmIsDead(true);
				mPlayer.setmIsDead(true);
				mPlayer.getDstRect().set(0,0,0,0); //invisible 처리
				//터치제어는 onTouchEvent에서
				//폭발처리
				addExplosion(mPlayer);
				//게임 종료 처리
				mState = STATE_OVER;
			}
		}
		
		//적군미사일과 아군 충돌체크
		for(Missile enemyMissile : mEnemyMissileList) {
			if(checkBoxToBox(enemyMissile, mPlayer)) {
				enemyMissile.setmIsDead(true);
				mPlayer.setmIsDead(true);
				mPlayer.getDstRect().set(0,0,0,0); //invisible 처리
				//터치제어는 onTouchEvent에서
				//폭발처리
				addExplosion(mPlayer);
				//게임 종료 처리
				mState = STATE_OVER;
			}
		}
	}

	private void addExplosion(Sprite s) {
		//폭발효과 처리
		Explosion explosion = new Explosion(mAppDirector.explosion_bitmap);
		explosion.init(6, 100, 66, 104, 3);
		explosion.setPosition(s.getmX()+s.getmWidth()/2, 
				s.getmX()+s.getmHeight()/2, 120, 200);
		mExplosionList.add(explosion);
		//폭발음효과
		if(mAppDirector.getSound())
			mAppDirector.playSoundEffect(AppDirector.SOUND_EXPLOSION);
	}
	
	private boolean checkBoxToBox(Sprite s1, Sprite s2) {
		if(s1.getDstRect().intersect(s2.getDstRect())) {
			return true;
		} else {
			return false;
		}
	}
}
