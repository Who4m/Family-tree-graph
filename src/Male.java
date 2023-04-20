public class Male extends Person {
	public Male(String name, String lastName, Date date, long ID) {
		super(name, lastName, date, ID);
	}

	public char gender() {
		return 'M';
	}

}
