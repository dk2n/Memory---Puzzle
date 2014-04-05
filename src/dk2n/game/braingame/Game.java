package dk2n.game.braingame;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity {
	//private static final String TAG = "BrainGame";
	
	public static final String KEY_DIFF = "dk2n.game.braingame.difficult";
	public static final int DIFF_EASY   = 0;
	public static final int DIFF_MEDIUM = 1;
	public static final int DIFF_HARD   = 2;
	
	//private static final String PREF_GAME = "puzzle";
	protected static final int DIFF_CONTINUE = -1;
	
	//private int puzzle[];
	private GameView myGameView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		myGameView = new GameView(this);
		setContentView(myGameView);
	}
	
	protected void onPause() {
		super.onPause();
	}
	
	protected void onResume() {
		super.onResume();
	}
}
