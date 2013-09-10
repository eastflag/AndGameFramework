package com.eastflag.gameframework;

public class AppDirector extends Object {
	
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
	
	
}
