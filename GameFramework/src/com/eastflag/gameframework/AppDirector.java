package com.eastflag.gameframework;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;

public class AppDirector {
	//디바이스 정보
	private int mWidth; 
	private int mHeight;
	//작업가상공간 설정하기 :FHD로 가정
	private int mVirtualWidth = 1080;
	private int mVirtualHeight =1920;
	
	//싱글턴 패턴----------
	//앱 전체에 반드시 하나만 존재.
	//=> 외부에서 new로 인스턴스를 생성못하게한다. =>생성자 private
	private AppDirector() {
		
	}
	private static AppDirector sAppDirector;
	public static  AppDirector  getInstance() {
		if(sAppDirector == null) {
			synchronized (AppDirector.class) {
				if(sAppDirector  == null) {
					sAppDirector = new AppDirector();
				}
			}
		}
		return sAppDirector;
	}
	//싱글턴 패턴 종료---------------------------------------------------
	
	public void initialize() {
		//디바이스 정보 얻기
		DisplayMetrics metrics = new DisplayMetrics(); 
		mMainActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics); 
		mWidth = metrics.widthPixels;
		mHeight = metrics.heightPixels;
		//비트맵 로딩
		
		//사운드 로딩
	}
	
	public MotionEvent convertEvent(MotionEvent event) {
		MotionEvent e = MotionEvent.obtain(event);
		//변환된 x, y = 작업공간/디바이스*event
		e.setLocation((float)mVirtualWidth/mWidth*event.getX(), 
				(float)mVirtualHeight/mHeight*event.getY());
		Log.d("ldk", "mWidth:" + mWidth + "mHeight:" + mHeight );
		Log.d("ldk", "변환된 x:" + e.getX() + "y:" + e.getY());
		return e;
	}
	
	private long mDeltaTime;
	public long getmDeltaTime() {
		return mDeltaTime;
	}
	public void setmDeltaTime(long mDeltaTime) {
		this.mDeltaTime = mDeltaTime;
	}
	
	private GameView mGameView;
	public GameView getmGameView() {
		return mGameView;
	}
	public void setmGameView(GameView mGameView) {
		this.mGameView = mGameView;
	}
	
	private MainActivity mMainActivity;
	public MainActivity getmMainActivity() {
		return mMainActivity;
	}
	public void setmMainActivity(MainActivity mMainActivity) {
		this.mMainActivity = mMainActivity;
	}
	
}
