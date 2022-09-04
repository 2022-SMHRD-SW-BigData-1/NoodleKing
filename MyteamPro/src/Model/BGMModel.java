package Model;

public class BGMModel { // Model

		private String musicName;
		private String singer;
		private int playTime;
		private String musicPath;

		public BGMModel(String musicPath) {
			super();
			this.musicPath = musicPath;
		}
		public BGMModel(String musicName, String singer, int playTime, String musicPath) {
			super();
			this.musicName = musicName;
			this.singer = singer;
			this.playTime = playTime;
			this.musicPath = musicPath;
		}
		
		public String getMusicName() {
			return musicName;
		}

		public String getSinger() {
			return singer;
		}

		public int getPlayTime() {
			return playTime;
		}

		public String getMusicPath() {
			return musicPath;
		}

}
