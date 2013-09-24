package com.eastflag.gameframework.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteObject extends Sprite{
	private Bitmap mBitmap;
	private Rect srcRect;
	
	public SpriteObject(Bitmap bitmap) {
		mBitmap = bitmap;
		srcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	}
	
	public void present(Canvas canvas) {
		canvas.drawBitmap(mBitmap, srcRect, dstRect, null);
	}
}
