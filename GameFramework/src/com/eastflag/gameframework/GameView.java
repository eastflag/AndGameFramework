package com.eastflag.gameframework;

import com.eastflag.gameframework.scene.IScene;
import com.eastflag.gameframework.scene.SceneMenu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

//SurfaceView 상속: 필름
//Callback 구현 : 필름의 상태가 바뀔때 호출되는 인터페이스. 3가지의 필름 상태를 정의
public class GameView extends SurfaceView implements Callback{
	RenderingThread mRenderingThread;
	Paint mPaint;
	
	//씬 정의
	private IScene mIScene;

	public GameView(Context context) {
		super(context);
		
		//1. 필름과 연필(mHolder)을 만들어서 애니메이션 작가를 고용해서 넘겨줌
		SurfaceHolder mHolder = getHolder();
		mHolder.addCallback(this);
		mRenderingThread = new RenderingThread(this, mHolder);
		
		mIScene = new SceneMenu(); //최초 시작시에는 메뉴 씬으로 시작
		
		//초기화
		setFocusable(true);
		AppDirector.getInstance().setmGameView(this);
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
		
		boolean retry  = true;
		while(retry){
		try {
			mRenderingThread.join();   //쓰레드를 정지
			retry = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
		}
	}
	
	//3. 필름의 상태 업데이트
	public void update() {
		mIScene.update();
	}
	
	//4. 필름에 그림 그리기
	public void present(Canvas canvas){
		mIScene.present(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mIScene.onTouchEvent(event);
		return true; //이벤트를 더이상 위로 발생시키지 않고 내가 처리하고 끝냄.
	}

	public void changeScene(IScene scene){
		//씬전환하는 코드 
		mIScene = scene;
	}
}
