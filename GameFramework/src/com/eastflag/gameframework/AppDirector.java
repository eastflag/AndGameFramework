package com.eastflag.gameframework;

import java.io.IOException;
import java.util.HashMap;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;

public class AppDirector {
	public static final int SOUND_MY_MISSILE = 1; //아군 미사일
	public static final int SOUND_ENEMY_MISSILE = 2; //적군군 미사일
	public static final int SOUND_EXPLOSION = 3; //폭발음
	
	//디바이스 정보
	private int mWidth; 
	private int mHeight;
	//작업가상공간 설정하기 :FHD로 가정
	public int mVirtualWidth = 1080;
	public int mVirtualHeight =1920;
	
	//Sound
	private HashMap<Integer, Integer> mSoundMap = new HashMap<Integer, Integer>();
	private SoundPool mSoundPool;
	
	//Bitmap
	//public Bitmap menuNew, menuNewOn; // new game 메뉴
	public Bitmap backGround, backCloud; //백그라운드
	public Bitmap player;;
	public Bitmap missile; //아군 미사일
	public Bitmap upTriangle, rightTriangle, downTriangle, leftTriangle; //상하좌우키패드
	public Bitmap circle; //미사일 발사 키패드
	public Bitmap enemy1, enemy2, enemy3;
	
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
		
		//사운드(효과음) 로딩
		mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
		int missile1 = mSoundPool.load(mMainActivity, R.raw.missile1, 1); //아군 미사일 사운드
		mSoundMap.put(SOUND_MY_MISSILE, missile1);
		int missile2 = mSoundPool.load(mMainActivity, R.raw.missile2, 1); //적군 미사일 사운드
		mSoundMap.put(SOUND_ENEMY_MISSILE, missile1);
		int explosion = mSoundPool.load(mMainActivity, R.raw.explosion, 1); //아군 미사일 사운드
		mSoundMap.put(SOUND_EXPLOSION, explosion);
		
		//비트맵 로딩
		AssetManager am = mMainActivity.getAssets();
		try {
			backGround = BitmapFactory.decodeStream(am.open("background2.jpg"));
			backCloud = BitmapFactory.decodeStream(am.open("background_2.png"));
//			menuNew = BitmapFactory.decodeStream(am.open("btn00.png"));
//			menuNewOn =  BitmapFactory.decodeStream(am.open("btn01.png"));
			player =  BitmapFactory.decodeStream(am.open("player.png"));
			missile =  BitmapFactory.decodeStream(am.open("missile_1.png"));
			
			upTriangle =  BitmapFactory.decodeStream(am.open("triangle.png"));
			Matrix m = new Matrix();
			
			m.postRotate(90, upTriangle.getWidth()/2, upTriangle.getHeight()/2);
			rightTriangle = Bitmap.createBitmap(upTriangle, 0, 0, upTriangle.getWidth(), upTriangle.getHeight(), m, false);
			
			m.postRotate(90, upTriangle.getWidth()/2, upTriangle.getHeight()/2);
			downTriangle = Bitmap.createBitmap(upTriangle, 0, 0, upTriangle.getWidth(), upTriangle.getHeight(), m, false);
			
			m.postRotate(90, upTriangle.getWidth()/2, upTriangle.getHeight()/2);
			leftTriangle = Bitmap.createBitmap(upTriangle, 0, 0, upTriangle.getWidth(), upTriangle.getHeight(), m, false);
			
			circle =  BitmapFactory.decodeStream(am.open("circle.png"));
			
			enemy1 =  BitmapFactory.decodeStream(am.open("enemy1.png"));
			enemy2 =  BitmapFactory.decodeStream(am.open("enemy2.png"));
			enemy3 =  BitmapFactory.decodeStream(am.open("enemy3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void playSoundEffect(int soundID) {
		Integer id =mSoundMap.get(soundID);
		mSoundPool.play(id, 1, 1, 0, 0, 1);
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
