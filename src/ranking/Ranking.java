package ranking;

public class Ranking {
	private String title;
	private int ratio;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public Ranking(String title, int ratio) {
		super();
		this.title = title;
		this.ratio = ratio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ranking))
			return false;
		Ranking other = (Ranking) obj;
		if (ratio != other.ratio)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ranking [title=" + title + ", ratio=" + ratio + "]";
	}

}
