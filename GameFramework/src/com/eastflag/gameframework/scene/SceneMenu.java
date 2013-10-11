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
	
	private ImageButton btnBgm, btnSound;
	
	private SpriteObject missile;
	
	public SceneMenu() {
		mAppDirector = AppDirector.getInstance();

		//메뉴
		mMenuShoot = new TextButton("Shooting");
		mMenuShoot.setPosition(540, 600, 700, 100);
		mMenuBoard = new TextButton("Board");
		mMenuBoard.setPosition(540, 1200, 700, 100);
		
		//Bgm, Sound
		btnBgm = new ImageButton(mAppDirector.bgmOn, mAppDirector.bgmOff);
		btnBgm.setPosition(100, 1800, 128, 128);
		btnBgm.isClicked = mAppDirector.getBGM() == true? false : true;
		
		btnSound = new ImageButton(mAppDirector.soundOn, mAppDirector.soundOff);
		btnSound.setPosition(250, 1800, 128, 128);
		
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
		
		btnBgm.present(canvas);
		btnSound.present(canvas);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		//게임 시작 화면으로 씬 전환
		//AppDirector.getInstance().getmGameView().changeScene(new SceneStart());
		//버튼 클릭 유무 체크
		//버튼 클릭이 된거면, down이벤트시 해당 버튼의 bitmap을 바꾸고, up 이벤트에서 원복
		
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(mMenuShoot.isSelected(event) == MotionEvent.ACTION_DOWN) {
				mMenuShoot.isClicked = true;
			}
			if(mMenuBoard.isSelected(event) == MotionEvent.ACTION_DOWN	) {
				mMenuBoard.isClicked = true;
			}
			if(btnBgm.isSelected(event) == MotionEvent.ACTION_DOWN) {
				if(btnBgm.isClicked) {
					mAppDirector.setBGM(true);
					mAppDirector.getmMainActivity().playBGM();
					btnBgm.isClicked = false;
				} else {
					mAppDirector.setBGM(false);
					mAppDirector.getmMainActivity().pauseBGM();
					btnBgm.isClicked = true;
				}
			}
			if(btnSound.isSelected(event) == MotionEvent.ACTION_DOWN) {
				if(btnSound.isClicked) {
					mAppDirector.setSound(true);
					btnSound.isClicked = false;
				} else {
					mAppDirector.setSound(false);
					btnSound.isClicked = true;
				}
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			//다운이 버튼에서 일어난후 move로 버튼을 벗어날시 처리
			mMenuShoot.isClicked = false;
			mMenuBoard.isClicked = false;
			
			if(mMenuShoot.isSelected(event) == MotionEvent.ACTION_UP) {
				AppDirector.getInstance().getmGameView().changeScene(new SceneShoot());
			}
			
			if(mMenuBoard.isSelected(event) == MotionEvent.ACTION_UP) {
				AppDirector.getInstance().getmGameView().changeScene(new SceneBoard());
			}
			break;
		}
		


	}

}
