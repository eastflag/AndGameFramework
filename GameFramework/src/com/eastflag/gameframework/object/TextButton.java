package com.eastflag.gameframework.object;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class TextButton extends Sprite {
	
	private String mTitle; // 제목글자
	private Paint backgroundPaint;  //백그라운드 색깔
	private Paint backgroundOnPaint; //클릭시 백그라운드 색깔
	private Paint fontPaint;
	
	//생성자 초기화는 필수 요소만 초기화
	public TextButton(String title) {
		mTitle = title;
		backgroundPaint = new Paint();
		backgroundPaint.setARGB(255, 255, 0, 255);
		backgroundOnPaint = new Paint();
		backgroundOnPaint.setARGB(255, 0, 255, 255);
		fontPaint = new Paint();
		fontPaint.setColor(Color.WHITE);
		fontPaint.setTextSize(80); //mHeight * 0.5
		fontPaint.setTextAlign(Align.CENTER);
	}
	
	//초기화 함수 :  옵션요소 초기화
	public void init() {
		
	}
	
	public void present(Canvas canvas) {
		//1) 사각형 그리기
		if(isOn) {
			canvas.drawRect(dstRect, backgroundOnPaint);
		} else {
			canvas.drawRect(dstRect, backgroundPaint);
		}
		//2) 사각형 정중앙에 제목 그리기
		canvas.drawText(mTitle, mX+mWidth/2, mY+65, fontPaint);  //Y: textSize * 0.4
	}
	
}
