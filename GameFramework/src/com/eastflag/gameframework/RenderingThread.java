package com.eastflag.gameframework;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class RenderingThread extends Thread {
	
	private volatile boolean mIsRunning = true;
	private GameView mGameView;
	private SurfaceHolder mSurfaceHolder;
	
	private  long deltaTime = 1;

	public RenderingThread(GameView gameView, SurfaceHolder mSurfaceHolder) {
		mGameView = gameView;
		this.mSurfaceHolder = mSurfaceHolder;
	}

	@Override
	public void run() {
		
		// 한번의 루프가 애니메이션 작가가 한장의 그림을 그린다. => deltaTime
		//1. 상태 업데이트 & 그리기
		Canvas canvas = null;  //도화지 생성
		long currTime = 0;
		while(mIsRunning){
			currTime = System.currentTimeMillis();
			try {
				// 도화지를 정지시키고 그림그리기
				canvas = mSurfaceHolder.lockCanvas();
				// update
				mGameView.update();

				// present
				mGameView.present(canvas);
				// 도화지를 떼내서 필름에 갖다 붙이기
			} catch (Exception e) {

			} finally {
				mSurfaceHolder.unlockCanvasAndPost(canvas);
			}
			
			deltaTime = System.currentTimeMillis() - currTime;
			AppDirector.getInstance().setmDeltaTime(deltaTime);
			//Log.d("ldk", "deltaTime:" + deltaTime); 
		}

	}
	
	public void setRunning(boolean isRunning) {
		mIsRunning = isRunning;
	}

}
