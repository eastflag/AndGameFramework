package com.eastflag.gameframework.object;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class TextObject extends Sprite{
	private String title;
	private Paint fontPaint;
	private int fontSize;
	
	public TextObject(String title, int fontColor, int fontSize) {
		this.title = title;
		this.fontSize = fontSize;
		
		fontPaint = new Paint();
		fontPaint.setColor(fontColor);
		fontPaint.setTextAlign(Align.CENTER);
		fontPaint.setTextSize(fontSize);
	}
	
	public void present(Canvas canvas) {
		canvas.drawText(title, mX+mWidth/2, mY+mHeight/2+(fontSize*0.4f), fontPaint);
	}

}
