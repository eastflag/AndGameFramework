package com.eastflag.gameframework.scene;

import com.eastflag.gameframework.AppDirector;
import com.eastflag.gameframework.object.Background;
import com.eastflag.gameframework.object.ImageButton;
import com.eastflag.gameframework.object.SpriteObject;
import com.eastflag.gameframework.object.TextButton;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class SceneMenu implements IScene {
	
	private AppDirector mAppDirector;

	//private ImageButton mMenuNew;
	private TextButton mMenuShoot;
	private TextButton mMenuBoard;
	
	private SpriteObject missile;
	
	public SceneMenu() {
		mAppDirector = AppDirector.getInstance();

		//메뉴
		mMenuShoot = new TextButton("Shooting");
		mMenuShoot.setPosition(540, 600, 700, 100);
		mMenuBoard = new TextButton("Board");
		mMenuBoard.setPosition(540, 1200, 700, 100);
		//mMenuNew = new ImageButton(mAppDirector.menuNew, mAppDirector.menuNewOn);
		//mMenuNew.setPostion(200, 400);
		//mMenuNew.setPosition(AppDirector.getInstance().mVirtualWidth/2, 500, 800, 200);
	
		//missile = new SpriteObject(AppDirector.getInstance().missile);
		//missile.setPosition(540, 540, 100, 100);
	}

	@Override
	public void update() {

	}

	@Override
	public void present(Canvas canvas) {
		canvas.drawColor(Color.DKGRAY);

		//mMenuNew.present(canvas);
		mMenuShoot.present(canvas);
		mMenuBoard.present(canvas);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		//게임 시작 화면으로 씬 전환
		//AppDirector.getInstance().getmGameView().changeScene(new SceneStart());
		//버튼 클릭 유무 체크
		//버튼 클릭이 된거면, down이벤트시 해당 버튼의 bitmap을 바꾸고, up 이벤트에서 원복
		
		if(mMenuShoot.isSelected(event) == MotionEvent.ACTION_UP) {
			AppDirector.getInstance().getmGameView().changeScene(new SceneShoot());
		}
		
		if(mMenuBoard.isSelected(event) == MotionEvent.ACTION_UP) {
			AppDirector.getInstance().getmGameView().changeScene(new SceneBoard());
		}

	}

}
