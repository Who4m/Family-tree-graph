import java.util.ArrayList;

public class MarriagesArchive {

	static MarriagesArchive obj = new MarriagesArchive();
	ArrayList<Person> bracnaArhiva;

	private MarriagesArchive() {
		bracnaArhiva = new ArrayList<Person>();
	}

	public static MarriagesArchive getInstance() {
		return obj;
	}

	public void merry(Person p1, Person p2) {

		if (((p1.getSpouse() != null) || (p2.getSpouse() != null)) || (p1.gender() == p2.gender())
				|| (p1.getLastName() == p2.getLastName())) {
			System.out.println("Sklapanje braka nije moguce");
		} else {
			p1.setSpouse(p2);
			p2.setSpouse(p1);
			bracnaArhiva.add(p1);
			bracnaArhiva.add(p2);
		}
	}

	public void divorce(Person p1) {
		Person spouse;
		if (p1.getSpouse() == null) {
			System.out.println("Ova osoba nije u braku");
		} else {
			spouse = p1.getSpouse();
			p1.setSpouse(null);
			spouse.setSpouse(null);
			ArrayList<Integer> pozicija = new ArrayList<Integer>(2);

			for (int i = 0; i < bracnaArhiva.size(); i++) {
				if (bracnaArhiva.get(i) == p1 || bracnaArhiva.get(i) == spouse) {
					pozicija.add(i);
				}
			}
			int prvi = pozicija.get(0);
			int drugi = pozicija.get(1);
			bracnaArhiva.remove(drugi);
			bracnaArhiva.remove(prvi);
		}
	}

	public static Person getSpaus(Person p1) {
		if (p1.getSpouse() == null) {
			System.out.println("Ova osoba nije u braku");
			return null;
		} else {
			return p1.getSpouse();
		}
	}

	public Person getSpaus(Long ID) {
		for (int i = 0; i < bracnaArhiva.size(); i++) {
			if (bracnaArhiva.get(i).getID() == ID) {
				return bracnaArhiva.get(i).getSpouse();
			}
		}
		return null;
	}

	public ArrayList<Person> getMarriedWomen() {
		ArrayList<Person> vracam = new ArrayList<Person>();
		for (int i = 0; i < bracnaArhiva.size(); i++) {
			if (bracnaArhiva.get(i).gender() == 'Z') {
				vracam.add(bracnaArhiva.get(i));
			}
		}
		return vracam;
	}

	public ArrayList<Person> getMarriedMen() {
		ArrayList<Person> vracam = new ArrayList<Person>();
		for (int i = 0; i < bracnaArhiva.size(); i++) {
			if (bracnaArhiva.get(i).gender() == 'M') {
				vracam.add(bracnaArhiva.get(i));
			}
		}
		return vracam;
	}

	public String toString() {
		String odgovor = "";
		for (int i = 0; i < bracnaArhiva.size(); i++) {
			odgovor += " " + bracnaArhiva.get(i) + " \n";
		}
		return odgovor;
	}
}
