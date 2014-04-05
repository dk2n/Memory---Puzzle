package dk2n.game.braingame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameView extends View {

	private static final String TAG = "GAMEVIEW";
	private final int BSIZE = 4;			// The size of the board.
	public static final int RANGE = 50;
	
	private final Game game;
	private List<Integer>myList = new ArrayList<Integer>();
	
	
	private Cell gameBoard[][] = new Cell[BSIZE][BSIZE];
	private Cell targetCell = new Cell();
	private static final int ID = 42;
	
	private float wCell;	// width of one tile (Cell).
	private float hCell;	// height of one tile (Cell).
	
	private float xStart;
	
	private int selX;		// X index of selection.
	private int selY;		// Y index of selection.
	private Canvas cVas;
	
	private Context context;
	public GameView(Context context) {
		super(context);
		this.game = (Game)context;
		this.context = context;

		generateRandom();
		setFocusable(true);
		setFocusableInTouchMode(true);
		setId(ID);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// Cell's dimension.
		wCell = w / BSIZE;
		hCell = h - w;
		
		xStart = w / 2f;
		
		Log.d(TAG, "" + w + " " + h);
		Log.d(TAG, "" + wCell + " " + hCell);
		//getRect(selX, selY, selRect);
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		this.cVas = canvas;
		cVas.drawColor( Color.BLACK );			
		for(int i = 0; i < gameBoard.length; i++) {
			for( int j = 0; j < gameBoard[i].length; j++) {
				if(gameBoard[i][j] == null ) {
					gameBoard[i][j] = new Cell(i*wCell, j*wCell+hCell, wCell);
				}
				if(gameBoard[i][j].getId() == -1) {
					gameBoard[i][j].setId(myList.get(i*j+j));
					Log.i(TAG, "index " + (i*j+j) + " value: " + myList.get(i*j+j));
				}
				gameBoard[i][j].drawCell(cVas);
			}
		}
		
		// Draw the target Cell.
		float what = hCell*7/10;
		targetCell.setX(xStart - what/2);
		targetCell.setY(0);
		targetCell.setW(what);
		Random rand = new Random();
		int x1 = rand.nextInt(BSIZE);
		int y1 = rand.nextInt(BSIZE);
		//Toast.makeText(context, "" + (xStart - wCell / 2), Toast.LENGTH_LONG).show();
		if(targetCell.getId() == -1) {
			targetCell.setId(gameBoard[x1][y1].getId());
		}
		targetCell.setTouch(true);
		targetCell.drawCell(cVas);
	}
	
	private void generateRandom() {
		Random rand = new Random();
		for( int i = 0; i < BSIZE*BSIZE; i++){
			myList.add(rand.nextInt(RANGE));
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() != MotionEvent.ACTION_DOWN) {
			return super.onTouchEvent(event);
		}
		
		// Get the Cell's position.
		selX = (int)((event.getX()) / wCell);
		selY = (int)((event.getY() - hCell) / wCell);
		
		
	
		if( selX > BSIZE || selY > BSIZE || selY < 0) { 
			return false;
		}
		else {
			cellSelection(selX, selY);
			if(gameBoard[selX][selY].getId() == targetCell.getId()) {
				int index = myList.indexOf(targetCell.getId());
				//myList.set(index, new Random().nextInt(RANGE));
				Toast.makeText(context, "Found it at index: " + index, Toast.LENGTH_LONG).show();
			}
		}
		Log.d(TAG, "selX: " + selX + " selY: " + selY);
		
		return true;
		
	}
	
	/** Perform the animation on a particular cell. */
	private void cellSelection( int x, int y) {
		if(gameBoard[x][y].getTouch()) {
			gameBoard[x][y].setTouch(false);
		} else {
			gameBoard[x][y].setTouch(true);
		}
		gameBoard[x][y].drawCell(cVas);
		invalidate();
	}
}
