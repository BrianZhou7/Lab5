import java.util.Arrays;

public class Song {
	
	private static final String INFO_DELIMITER = "; ";
	private static final String TIME_DELIMITER = ":";
	private static final int IDX_TITLE = 0;
	private static final int IDX_ARTIST = 1;
	private static final int IDX_TIME = 2;
	
	
	private String title;
	private String artist;
	private int[] time;
	
	
	
	
	public Song(String title, String artist, int[] time) {
		this.title = title;
		this.artist = artist;
		this.time = Arrays.copyOf(time, time.length);
	}
	
	public Song(String info) {
	    String[] infoParts = info.split(INFO_DELIMITER);

	    title = infoParts[IDX_TITLE];
	    artist = infoParts[IDX_ARTIST];

	    String[] timeParts = infoParts[IDX_TIME].split(TIME_DELIMITER);

	    time = new int[timeParts.length];

	    for (int i = 0; i < timeParts.length; i++) {
	        time[i] = Integer.parseInt(timeParts[timeParts.length - 1 - i]);
	    }
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public int[] getTime() {
		return Arrays.copyOf(time, time.length);
	}
	
	@Override
	public String toString() {
	    StringBuilder result = new StringBuilder();

	    result.append(title);
	    result.append(INFO_DELIMITER);
	    result.append(artist);
	    result.append(INFO_DELIMITER);

	    for (int i = time.length - 1; i >= 0; i--) {
	        if (i == time.length - 1) {
	            result.append(time[i]);
	        } else {
	            result.append(TIME_DELIMITER);
	            result.append(String.format("%02d", time[i]));
	        }
	    }

	    return result.toString();
	}
	
	
}
