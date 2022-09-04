package Controller;

import java.util.ArrayList;

import Model.BGMModel;
import javazoom.jl.player.MP3Player;

public class BGM { // Controller
	ArrayList<BGMModel> musicList = new ArrayList<BGMModel>();
	MP3Player mp3 = new MP3Player();
	int currentIndex = 0;
	
	public BGM() {
		musicList.add(new BGMModel("bgm//MainBGM.mp3"));
		musicList.add(new BGMModel("bgm//브금3.mp3"));
		musicList.add(new BGMModel("bgm//minigame.mp3"));
		musicList.add(new BGMModel("bgm//Rain - 깡.mp3"));
		musicList.add(new BGMModel("bgm//sueBGM.mp3"));
		musicList.add(new BGMModel("bgm//rank.mp3"));		
	}
	
	public BGMModel play(int num) {
		BGMModel m = musicList.get(num-1);
		
		if(mp3.isPlaying()) {
			mp3.stop();
		}
		
		mp3.play(m.getMusicPath());
		
		return m;
		
	}
	public BGMModel play() {
		BGMModel m = musicList.get(currentIndex);
		
		if(mp3.isPlaying()) {
			mp3.stop();
		}
		
		mp3.play(m.getMusicPath());
		
		return m;
		
	}
	
	public String stop() {
		mp3.stop();
		String message = "노래가 정지되었습니다.";
		return message;
	}
	
}
