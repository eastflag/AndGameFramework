package com.eastflag.gameframework;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

public class RenderingThread extends Thread {
	
	private volatile boolean mIsRunning = true;
	private GameView mGameView;
	private SurfaceHolder mSurfaceHolder;
	
	private  long deltaTime = 1;
	
	private Canvas virtualCanvas; //가상 작업 공간
	private Rect dstRect; //실제 디바이스
	private Bitmap mBitmap;

	public RenderingThread(GameView gameView, SurfaceHolder mSurfaceHolder) {
		mGameView = gameView;
		this.mSurfaceHolder = mSurfaceHolder;
		
		//작업공간을 정의
		mBitmap = Bitmap.createBitmap(1080, 1920, Config.ARGB_8888);
		//숙제 : 하드코딩 제거, AppDirector로 이동기
		virtualCanvas = new Canvas();
		virtualCanvas.setBitmap(mBitmap);
		dstRect = new Rect();
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
				//mGameView.present(canvas);
				mGameView.present(virtualCanvas);
				// 도화지를 떼내서 필름에 갖다 붙이기
			} catch (Exception e) {

			} finally {			
				if (canvas != null) {
					// 작업공간을 실제 디바이스 크기로 늘리기
					canvas.getClipBounds(dstRect); // dstRect에 실제 디바이스크기를 할당
					// 숙제 : dstRect 크기 Log.d로 찍어보기
					canvas.drawBitmap(mBitmap, null, dstRect, null); // 작업공간을 실제 디바이스로  늘리기

					// 다 그려진 도화지를 떼서 필름에 갖다 붙이기 (최종 작업)
					mSurfaceHolder.unlockCanvasAndPost(canvas);
				}
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
