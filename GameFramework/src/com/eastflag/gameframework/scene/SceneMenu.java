package com.eastflag.gameframework.scene;

import com.eastflag.gameframework.AppDirector;
import com.eastflag.gameframework.object.Background;
import com.eastflag.gameframework.object.ImageButton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class SceneMenu implements IScene {
	
	private AppDirector mAppDirector;
	private Background mBack, mBackCloud;
	private ImageButton mMenuNew;
	
	public SceneMenu() {
		mAppDirector = AppDirector.getInstance();
		//백그라운드
		mBack = new Background(mAppDirector.backGround);
		mBack.setSpeed(1);
		mBackCloud = new Background(mAppDirector.backCloud);
		mBackCloud.setSpeed(-1);
		//메뉴
		mMenuNew = new ImageButton(mAppDirector.menuNew, mAppDirector.menuNewOn);
		//mMenuNew.setPostion(200, 400);
		mMenuNew.setPosition(AppDirector.getInstance().mVirtualWidth/2, 500, 800, 200);
	}

	@Override
	public void update() {
		mBack.update();
		mBackCloud.update();
	}

	@Override
	public void present(Canvas canvas) {
		//canvas.drawColor(Color.DKGRAY);
		mBack.present(canvas);
		mBackCloud.present(canvas);
		mMenuNew.present(canvas);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		//게임 시작 화면으로 씬 전환
		//AppDirector.getInstance().getmGameView().changeScene(new SceneStart());
		//버튼 클릭 유무 체크
		//버튼 클릭이 된거면, down이벤트시 해당 버튼의 bitmap을 바꾸고, up 이벤트에서 원복
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(mMenuNew.isClicked((int)event.getX(), (int)event.getY())) {
				//버튼 이미지 변경
			}
			break;
		
		}
	}

}
