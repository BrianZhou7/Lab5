import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Playlist {
	
	private ArrayList<Song> songs;
	
	public Playlist() {
        songs = new ArrayList<Song>();
    }
	
	public Playlist(String filename) throws IOException {
	    this();
	    addSongs(filename);
	}
	
	public int getNumSongs() {
		return songs.size();
	}
	
	public Song getSong(int index) {
		if (index < 0 || index >= getNumSongs()) {
			return null;
		}
		return songs.get(index);
	}
	
	public Song[] getSongs() {
		return songs.toArray(new Song[0]);
	}
	
	public boolean addSong(Song song) {
		return addSong(getNumSongs(), song);
	}
	
	public boolean addSong(int index, Song song) {

	    if (song == null || index < 0 || index > getNumSongs()) {
	        return false;
	    }

	    songs.add(index, song);

	    return true;
	}
	
	
	public int addSongs(Playlist playlist) {

	    if (playlist == null) {
	        return 0;
	    }

	    Song[] songsToAdd = playlist.getSongs();

	    for (Song song : songsToAdd) {
	        addSong(song);
	    }

	    return songsToAdd.length;
	}
	
	
	public Song removeSong() {
		return removeSong(getNumSongs() - 1);
	}
	
	public Song removeSong(int index) {

	    if (index < 0 || index >= getNumSongs()) {
	        return null;
	    }

	    return songs.remove(index);
	}
	
	public void addSongs(String filename) throws IOException {

	    BufferedReader reader = new BufferedReader(new FileReader(filename));

	    String line = reader.readLine();

	    while (line != null) {
	        addSong(new Song(line));
	        line = reader.readLine();
	    }

	    reader.close();
	}
	
	
	@Override
	public String toString() {
	    StringJoiner joiner = new StringJoiner(System.lineSeparator());

	    for (Song song : songs) {
	        joiner.add(song.toString());
	    }

	    return joiner.toString();
	}
	
	public void saveSongs(String filename) throws IOException {

	    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

	    writer.write(toString());

	    writer.close();
	}
	
	
	public int[] getTotalTime() {

	    int totalSeconds = 0;
	    int totalMinutes = 0;
	    int totalHours = 0;

	    for (Song song : songs) {
	        int[] time = song.getTime();

	        if (time.length > 0) {
	            totalSeconds += time[0];
	        }

	        if (time.length > 1) {
	            totalMinutes += time[1];
	        }

	        if (time.length > 2) {
	            totalHours += time[2];
	        }
	    }

	    totalMinutes += totalSeconds / 60;
	    totalSeconds %= 60;

	    totalHours += totalMinutes / 60;
	    totalMinutes %= 60;

	    if (totalHours > 0) {
	        return new int[] {totalSeconds, totalMinutes, totalHours};
	    } else if (totalMinutes > 0) {
	        return new int[] {totalSeconds, totalMinutes};
	    } else {
	        return new int[] {totalSeconds};
	    }
	}
	
	
}
