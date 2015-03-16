package com.hisham.edureka.drawingbrush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

public class MyTouchEventView extends View {

	private Paint paint = new Paint();
	private Path path = new Path();
	private Paint circlePaint = new Paint();
	private Path circlePath = new Path();

	public Button btnReset;
	public LayoutParams params;

	public MyTouchEventView(Context context) {
		super(context);

		paint.setAntiAlias(true);
		paint.setColor(Color.MAGENTA);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(5f);

		circlePaint.setAntiAlias(true);
		circlePaint.setColor(Color.BLUE);
		circlePaint.setStyle(Paint.Style.STROKE);
		circlePaint.setStrokeJoin(Paint.Join.MITER);
		circlePaint.setStrokeWidth(4f);

		btnReset = new Button(context);
		btnReset.setText("Clear Screen");

		params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		btnReset.setLayoutParams(params);

		btnReset.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				// resets the screen
				path.reset();

				// Calls the onDraw() method
				postInvalidate();

			}
		});

	}

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawPath(path, paint);
		canvas.drawPath(circlePath, circlePaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		// Gives you x and y coordinates on the Event.
		float pointX = event.getX();
		float pointY = event.getY();

		// Checks for the event that occurs
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(pointX, pointY);

			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(pointX, pointY);
			circlePath.reset();

			// (circle's center x-coordinate, y-coordinate, radius of the
			// circle, direction to wind the shape)
			circlePath.addCircle(pointX, pointY, 15, Path.Direction.CW);
			//circlePath.addRect(pointX - 25, pointY - 25, pointX + 25, pointY + 25, Path.Direction.CW);
/*			RectF rect = new RectF(pointX - 25, pointY - 25, pointX + 25, pointY + 25);
			circlePath.addRoundRect(rect, 0, 0, Path.Direction.CW);
*/
			break;

		case MotionEvent.ACTION_UP:
			circlePath.reset();

			break;
		default:
			return false;
		}

		// Schedules a repaint.
		// Force a view to draw.
		postInvalidate();
		return true;
	}
}
