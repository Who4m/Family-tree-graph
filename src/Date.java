
public class Date {
	private int day;
	private int month;
	private int year;

	public Date(int day, int month, int year) {
		if ((day > 31) || (day < 1) || (month > 12) || (month < 1)) {
			this.day = 1;
			this.month = 1;
			this.year = 1900;
			System.out.println("Pogresno unete vrednosti Default je: 01/01/1900");
		} else {
			this.day = day;
			this.month = month;
			this.year = year;
		}
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int ageDiference(Date datum) {
		int razlika = 0;
		if (this.year - datum.getYear() == 0) {
			razlika = 0;

		} else {

			razlika = this.year - datum.getYear();
		}
		return Math.abs(razlika);
	}

	public boolean isEqual(Date datum) {
		if ((this.year == datum.getYear()) && (this.month == datum.getMonth()) && (this.day == datum.getDay())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isLower(Date datum) {
		if (this.year < datum.getYear()) {
			return true;
		} else if (this.year > datum.getYear()) {
			return false;
		} else {
			if (this.month < datum.getMonth()) {
				return true;
			} else if (this.month > datum.getMonth()) {
				return false;
			} else {
				if (this.day < datum.getDay()) {
					return true;
				} else if (this.day > datum.getDay()) {
					return false;
				} else {
					return false;
				}
			}
		}
	}

	public boolean isGreater(Date datum) {
		return !(this.isEqual(datum) || this.isLower(datum));
	}

	public boolean isLowerOrEqual(Date datum) {
		return !(this.isGreater(datum));
	}

	public boolean isGreaterOrEqual(Date datum) {
		return !(this.isLower(datum));
	}

	public boolean isNotEqual(Date datum) {
		return !(this.isEqual(datum));
	}

	public String toString() {

		String odgovor;
		odgovor = day + "/" + month + "/" + year;
		return odgovor;
	}
}
