package com.eastflag.gameframework;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	//public static MainActivity mMainActivity;
	public int a = 1;
	AppDirector mAppDirector = AppDirector.getInstance();
	
	private MediaPlayer mMediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//FullScreen 만들기
		//타이틀바 없애기
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//안테나영역 없애기
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //초기화
        //디바이스 정보얻기, 비트맵 로딩, 사운드로딩
        AppDirector.getInstance().setmMainActivity(this);
        AppDirector.getInstance().initialize();
        
		//setContentView(R.layout.activity_main);
		//setContentView(new MyView(this));  //한장의 그림과 같음
		setContentView(new GameView(this));  //영화와 같음.
		
		mMediaPlayer = new MediaPlayer();
		
		AssetManager am = getAssets();
		try {
			AssetFileDescriptor fd =  am.openFd("bgm.mp3");
			mMediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
			mMediaPlayer.prepare();
			mMediaPlayer.setVolume(1f, 1f);
		} catch (IllegalStateException ise) {
			
		} catch (IllegalArgumentException iae) {
			
		} catch (IOException ioe) {
			
		} catch (Exception e) {
			
		}
		
		Log.d("ldk", "AppDirector 메모리 주소값:" + AppDirector.getInstance());
		Log.d("ldk", "AppDirector 메모리 주소값:" + AppDirector.getInstance());
	}
	
	

	@Override
	protected void onResume() {
		if(mAppDirector.getBGM()) {
			playBGM();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		pauseBGM();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		stopBGM();
		super.onDestroy();
	}
	
	public void playBGM() {
		try {
			mMediaPlayer.start();
		} catch(IllegalStateException ise) {
			
		}
	}
	
	public void pauseBGM() {
		try {
			mMediaPlayer.pause();
		} catch(IllegalStateException ise) {
			
		}
	}

	public void stopBGM() {
		try {
			mMediaPlayer.release();
		} catch(IllegalStateException ise) {
			
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			//다이얼로그 생성 (Builder패턴으로)
			finishApp();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private  void finishApp() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Game Over")
			.setMessage("게임을 정말로 종료하시겠습니까?")
			.setPositiveButton("OK", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// 종료처리
					finish();
				}
			})
			.setNegativeButton("Cancle", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			})
			.show();
	}

}

//View : 한장 짜리 그림
class MyView extends View {

	public MyView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//바탕색을 파란색으로 칠하기
		canvas.drawColor(Color.BLUE);
		//회색 원 그리기
		Paint paint = new Paint();  //붓
		paint.setColor(Color.LTGRAY);;
		canvas.drawCircle(200, 200, 100, paint);
		super.onDraw(canvas);
	}
}