package dk2n.game.braingame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button bt_contGame, bt_newGame, bt_about;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bt_contGame = (Button)findViewById(R.id.bt_cont);
		bt_contGame.setOnClickListener(this);
		
		bt_newGame  = (Button)findViewById(R.id.bt_newgame);
		bt_newGame.setOnClickListener(this);
		
		bt_about    = (Button)findViewById(R.id.bt_about);
		bt_about.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.bt_cont:
			startGame();
			break;
		case R.id.bt_newgame:
			startGame();
			break;
		case R.id.bt_about:
			Toast.makeText(this, "This is about button", Toast.LENGTH_LONG).show();
			break;
		}
	}
	
	
	private void startGame() {
		Intent intent = new Intent( this, Game.class);
		//Toast.makeText(this, "Let's play the game", Toast.LENGTH_LONG).show();
		startActivity(intent);
	}
	
	protected void onPause() {
		super.onPause();
	}
	
	protected void onResume() {
		super.onResume();
	}
}
