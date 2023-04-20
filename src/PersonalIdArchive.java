import java.util.ArrayList;

public class PersonalIdArchive {

	static PersonalIdArchive obj = new PersonalIdArchive();
	private ArrayList<Long> arhiva;
	private long next;

	private PersonalIdArchive() {
		arhiva = new ArrayList<Long>();
		next = 0;
	}

	public long nextID() {
		next++;
		return next;
	}

	public static PersonalIdArchive getInstance() {
		return obj;
	}

	public boolean containsID(long ID) {
		for (int i = 0; i < arhiva.size(); i++) {
			if (arhiva.get(i) == ID) {
				return true;
			}
		}
		return false;
	}

	public void addRecord(long ID) {
		if (containsID(ID) == false) {
			arhiva.add(ID);
		}
	}

	public ArrayList<Long> getRecords() {
		return arhiva;
	}

	public String toString() {
		String odgovor = "";
		for (int i = 0; i < arhiva.size(); i++) {
			odgovor += " " + arhiva.get(i) + " \n";
		}
		return odgovor;
	}
}
