package com.eastflag.gameframework;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	//public static MainActivity mMainActivity;
	public int a = 1;
	AppDirector mAppDirector;

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
		
		Log.d("ldk", "AppDirector 메모리 주소값:" + AppDirector.getInstance());
		Log.d("ldk", "AppDirector 메모리 주소값:" + AppDirector.getInstance());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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