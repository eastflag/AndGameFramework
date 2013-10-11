package com.eastflag.gameframework.object;

import java.util.Random;

import android.graphics.Bitmap;

public class Enemy extends SpriteAnimation{
	private long localTime;
	private long localMissileTime;
	public boolean makeMissile;
	private int Time_To_Dispaly_Enemy_Missile = 3000;
	
	public enum EnemyType {
		AccelType,  //화면의 반을 넘어서면 속도가 3배 빨라짐
		LeftType,    //화면의 반을 넘어서면 왼쪽으로 꺼어면서 2배 빨라짐
		RightType  //화면의 반을 넘어서면 오른쪽으로 꺽어면서 2배 빨라짐.
	}
	
	private EnemyType mEnemyType; //현재 인스턴스가 저장하는 적군타입
	
	//씬에서 적군타입을 랜덤으로 생성후 bitmap, type을 넘겨준다.
	public Enemy(Bitmap bitmap, int type) {
		super(bitmap);
		
		mEnemyType = EnemyType.values()[type];
	}
	
	public void update(){
		super.update();
		
		localTime += mAppDirector.getmDeltaTime();
		//10ms에 1px 움직이기
		while (localTime >= 10) {

			if (mEnemyType == EnemyType.AccelType) {
				if (mY >= 900) {
					mY += 3;
				} else {
					mY += 1;
				}
			}

			if (mEnemyType == EnemyType.LeftType) {
				if (mY >= 900) {
					mY += 2;
					mX += -2;
				} else {
					mY += 1;
				}
			}
			
			if (mEnemyType == EnemyType.RightType) {
				if (mY >= 900) {
					mY += 2;
					mX += 2;
				} else {
					mY += 1;
				}
			}

			// 화면에 안보일때 dead 처리
			if (mY > 1920 || mX < -mWidth || mX > 1080) {
				mIsDead = true;
				break;
			}
			localTime -= 10;
			dstRect.set(mX, mY, mX + mWidth, mY + mHeight);
		}

		// 3초마다 미사일 생성
		localMissileTime += mAppDirector.getmDeltaTime();
		while (localMissileTime >= Time_To_Dispaly_Enemy_Missile) {
			makeMissile = true;
			localMissileTime -= Time_To_Dispaly_Enemy_Missile;

			Random rand = new Random();
			Time_To_Dispaly_Enemy_Missile = (1 + rand.nextInt(5)) * 1000;
		}
	}
}
