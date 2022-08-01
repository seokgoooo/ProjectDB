package capitals;

// 수도 클래스
public class Capitals {
	private int number;
	private String question;
	private String answer;
	private String continent;

	public Capitals(int number, String question, String answer, String continent) {
		super();
		this.number = number;
		this.question = question;
		this.answer = answer;
		this.continent = continent;
	}

	public Capitals(String question, String answer, String continent) {
		super();
		this.question = question;
		this.answer = answer;
		this.continent = continent;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Capitals))
			return false;
		Capitals other = (Capitals) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (continent == null) {
			if (other.continent != null)
				return false;
		} else if (!continent.equals(other.continent))
			return false;
		if (number != other.number)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Capitals [number=" + number + ", question=" + question + ", answer=" + answer + ", continent="
				+ continent + "]";
	}

}
