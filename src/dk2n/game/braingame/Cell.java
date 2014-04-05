package dk2n.game.braingame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Cell {
	//private static final String TAG = "Cell";
	private float x;
	private float y;
	private float w;
	private int num;
	private boolean onTouch;
	
	public Cell() {	
		this.num = -1;
	}
	
	public Cell(float x, float y, float w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.onTouch = false;
		this.num = -1;
		//Random rand = new Random();
		//this.num = rand.nextInt(GameView.RANGE);
	}
	
	public void drawCell(Canvas canvas) {
		if(onTouch)
			drawForeground(canvas);
		else
			drawBackground(canvas);
	}
	
	/* This function is used to draw the cell to the canvas. */
	private void drawForeground(Canvas canvas){
		drawBackground(canvas);
		Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
		foreground.setColor(Color.WHITE);
		//foreground.setStyle(Style.FILL);
		foreground.setTextSize(w * 0.5f);
		foreground.setTextAlign(Paint.Align.CENTER);
		int xPos = (int)(x + (w/2));
		int yPos = (int)(y + (w/2) - (foreground.descent() + foreground.ascent())/2);
		canvas.drawText( String.valueOf(num), xPos, yPos, foreground);
	}
	
	/** Draw the background of the cell. */
	private void drawBackground(Canvas canvas) {
		int line = 4;
		Paint lineBorder = new Paint();
		lineBorder.setColor(Color.MAGENTA);
		canvas.drawRect(x, y, x+w, y+w, lineBorder);
		
		Paint background = new Paint();
		background.setColor(Color.BLUE);
		canvas.drawRect(x+line, y+line, x+(w-line), y+(w-line), background);
	}
	
	/** Accessors and Mutators field. */
	public void setId(int num) { this.num = num; }
	public int getId() { return this.num; }
	
	public void setX(float x) {	this.x = x;	}
	public float getX() { return this.x; }
	
	public void setY(float y) {	this.y = y;	}
	public float getY() { return this.y; }
	
	public void setW(float w) { this.w = w; }
	public float getW() { return this.w; }
	
	public void setTouch(boolean touch) { this.onTouch = touch; }
	public boolean getTouch() { return this.onTouch; }
}
