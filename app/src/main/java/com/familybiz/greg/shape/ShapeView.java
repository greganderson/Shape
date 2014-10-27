package com.familybiz.greg.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View;

public class ShapeView extends View {

	public final int SIDE_COUNT_MIN = 3;
	int mSideCount = SIDE_COUNT_MIN;

	public ShapeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setBackgroundColor(Color.GRAY);

		TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);
		int sideCount = attributes.getInteger(R.styleable.ShapeView_side_count, SIDE_COUNT_MIN);
		attributes.recycle();

		setSideCount(sideCount);
	}

	public int getSideCount() {
		return mSideCount;
	}

	public void setSideCount(int sideCount) {
		mSideCount = sideCount < SIDE_COUNT_MIN ? SIDE_COUNT_MIN : sideCount;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		RectF shapeRect = new RectF();
		shapeRect.left = getPaddingLeft();
		shapeRect.top = getPaddingTop();
		shapeRect.right = getWidth() - getPaddingRight();
		shapeRect.bottom = getHeight() - getPaddingBottom();

		Path shapePath = new Path();
		for (int pointIndex = 0; pointIndex < mSideCount; pointIndex++) {
			float angle = 2.0f * (float)Math.PI * ((float)pointIndex / (float)mSideCount);
			float pointX = shapeRect.centerX() + FloatMath.cos(angle) * 0.5f * shapeRect.width();
			float pointY = shapeRect.centerY() + FloatMath.sin(angle) * 0.5f * shapeRect.height();

			if (pointIndex == 0)
				shapePath.moveTo(pointX, pointY);
			else
				shapePath.lineTo(pointX, pointY);
		}

		shapePath.close();

		Paint shapePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		shapePaint.setStyle(Paint.Style.FILL);
		shapePaint.setColor(Color.GREEN);
		canvas.drawPath(shapePath, shapePaint);
	}

}
