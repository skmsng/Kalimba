package sample.application.kalimba;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class KalimbaActivity extends Activity implements OnClickListener {
	
	MediaPlayer[] mp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //配列リソース(mid,ボタン)の取得
        TypedArray notes = getResources().obtainTypedArray(R.array.notes);
		TypedArray buttons = getResources().obtainTypedArray(R.array.buttons);
		this.mp = new MediaPlayer[notes.length()];
		
		//mid配列リソースを配列に格納
		for(int i=0; i<notes.length(); i++)
			this.mp[i] = MediaPlayer.create(this,notes.getResourceId(i, -1));
		
		//ボタン配列リソースのリスナー設定
		for(int i=0; i<buttons.length(); i++)
			findViewById(buttons.getResourceId(i, -1)).setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		TypedArray buttons = getResources().obtainTypedArray(R.array.buttons);
		for(int i=0; i<buttons.length(); i++){
			if(buttons.getResourceId(i, -1) == v.getId()){
				this.mp[i].seekTo(0);
				this.mp[i].start();
				break;
			}
		}
	}
}
