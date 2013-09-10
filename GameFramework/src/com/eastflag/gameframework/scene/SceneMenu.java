package com.eastflag.gameframework.scene;

import com.eastflag.gameframework.AppDirector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class SceneMenu implements IScene {

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void present(Canvas canvas) {
		canvas.drawColor(Color.DKGRAY);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		//게임 시작 화면으로 씬 전환
		AppDirector.getInstance().getmGameView().changeScene(new SceneStart());
	}

}
