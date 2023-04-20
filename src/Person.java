
public abstract class Person {
	private String name;
	private String lastName;
	private Date dateOfBirth;
	private long ID;
	private Person spouse;

	public Person(String name, String lastName, Date date, long ID) {
		this.name = name;
		this.lastName = lastName;
		this.dateOfBirth = date;
		this.spouse = null;
		if (PersonalIdArchive.getInstance().containsID(ID) == true) {
			long noviID = PersonalIdArchive.getInstance().nextID();
			System.out.println("Ovaj id je bio zauzet pa smo vam dodelili default");
			while (PersonalIdArchive.getInstance().containsID(noviID) == true) {
				noviID = PersonalIdArchive.getInstance().nextID();
			}
			this.ID = noviID;
			PersonalIdArchive.getInstance().addRecord(noviID);

		} else {
			this.ID = ID;
			PersonalIdArchive.getInstance().addRecord(ID);
		}

	}

	public void setID(long ID) {
		this.ID = ID;
	}

	public abstract char gender();

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public String getFirstName() {
		return this.name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public long getID() {
		return this.ID;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public boolean isOlder(Person person) {
		return this.dateOfBirth.isLower(person.getDateOfBirth());
	}

	public boolean isYounger(Person person) {
		return this.dateOfBirth.isGreater(person.getDateOfBirth());
	}

	public boolean isOlderOrEqual(Person person) {
		return this.dateOfBirth.isLowerOrEqual(person.getDateOfBirth());
	}

	public boolean isYoungerOrEqual(Person person) {
		return this.dateOfBirth.isGreaterOrEqual(person.getDateOfBirth());
	}

	public boolean isEqual(Person person) {
		return this.dateOfBirth.isEqual(person.dateOfBirth);
	}

	public boolean isNotEqual(Person person) {
		return this.dateOfBirth.isNotEqual(person.getDateOfBirth());
	}

	public String toString() {
		String odgovor;
		odgovor = " " + ID + " " + name + " " + lastName + " " + gender() + " " + dateOfBirth;
		return odgovor;
	}

}
