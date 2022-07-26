package music;

public class Music {
	private int number;
	private String title;
	private String singer;
	private String genre;
	private int year;
	private int playCount;

	public Music(int number, String title, String singer, String genre, int year, int playCount) {
		super();
		this.number = number;
		this.title = title;
		this.singer = singer;
		this.genre = genre;
		this.year = year;
		this.playCount = playCount;
	}

	public Music(String title, String singer, String genre, int year, int playCount) {
		super();
		this.title = title;
		this.singer = singer;
		this.genre = genre;
		this.year = year;
		this.playCount = playCount;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public int getYear() {
		return year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPlayCount() {
		return playCount;
	}

	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Music))
			return false;
		Music other = (Music) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (number != other.number)
			return false;
		if (playCount != other.playCount)
			return false;
		if (singer == null) {
			if (other.singer != null)
				return false;
		} else if (!singer.equals(other.singer))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Music [number=" + number + ", title=" + title + ", singer=" + singer + ", year=" + year + ", playCount="
				+ playCount + "]";
	}

}
