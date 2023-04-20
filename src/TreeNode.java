
public class TreeNode {
	private Person me;
	private TreeNode olderSibiling;
	private TreeNode youngerSibiling;
	private TreeNode oldestChild;

	public TreeNode(Person person) {
		me = person;
		olderSibiling = null;
		youngerSibiling = null;
		oldestChild = null;
	}

	public TreeNode(TreeNode node) {
		this.me = node.getMe();
		olderSibiling = null;
		youngerSibiling = null;
		oldestChild = null;
	}

	public TreeNode getOlderSibiling() {
		return olderSibiling;
	}

	public TreeNode getYoungerSibiling() {
		return youngerSibiling;
	}

	public TreeNode getOldestChild() {
		return oldestChild;
	}

	public void setOlderSibiling(TreeNode node) {
		this.olderSibiling = node;
	}

	public void setYoungerSibiling(TreeNode node) {
		this.youngerSibiling = node;
	}

	public void setOldestChild(TreeNode node) {
		this.oldestChild = node;
	}

	public Person getMe() {
		return me;
	}

	public void addSibiling(Person sibiling) {
		int moze = 0;
		if (me.getDateOfBirth().ageDiference(sibiling.getDateOfBirth()) == 0) {
			System.out.println("Dodavanje nije moguce");
		} else if (me.isOlder(sibiling)) {
			// OVaj else if je ako je current stariji od target
			TreeNode current = this;
			TreeNode target = new TreeNode(sibiling);
			while (target.me.isYounger(current.getMe())) {
				if (current.me.getDateOfBirth().ageDiference(sibiling.getDateOfBirth()) == 0) {
					System.out.println("Dodavanje nije moguce");
					moze = 1;
					break;
				} else {
					if (current.getYoungerSibiling() == null) {
						break;
					} else {
						current = current.getYoungerSibiling();
					}

				}
			}
			if (moze == 0) {
				if (target.me.isOlder(current.getMe())) {
					target.setOlderSibiling(current.getOlderSibiling());
					current.setOlderSibiling(target);
					target.getOlderSibiling().setYoungerSibiling(target);
					target.setYoungerSibiling(current);
				} else if (current.getYoungerSibiling() == null) {
					current.setYoungerSibiling(target);
					target.setOlderSibiling(current);
				}
			}
		} else {
			// Ovaj else je ako sam current mladji od targeta
			TreeNode current = this;
			TreeNode target = new TreeNode(sibiling);
			while (target.me.isOlder(current.getMe())) {
				if (current.me.getDateOfBirth().ageDiference(sibiling.getDateOfBirth()) == 0) {
					moze = 1;
					break;
				} else {
					if (current.getOlderSibiling() == null) {
						break;
					} else {
						current = current.getOlderSibiling();
					}
				}
			}
			if (moze == 0) {
				if (target.me.isYounger(current.getMe())) {
					target.setYoungerSibiling(current.getYoungerSibiling());
					current.setYoungerSibiling(target);
					target.getYoungerSibiling().setOlderSibiling(target);
					target.setOlderSibiling(current);

				} else if (current.getOlderSibiling() == null) {
					target.setYoungerSibiling(current);
					current.setOlderSibiling(target);

				}
			}
		}
	}

	public void addChild(Person child) {
		// TODO ne dozvoli da je dete starije od roditelja!!!!!!!!!
		if (child.isOlder(this.me)) {
			System.out.println("Dodavanje nije moguce");
			return;
		} else {
			if (this.me.getDateOfBirth().ageDiference(child.getDateOfBirth()) < 18) {
				System.out.println("Dodavanje nije moguce");
			} else {
				if (this.oldestChild == null) {
					TreeNode najstariji = new TreeNode(child);
					this.oldestChild = najstariji;
				} else {
					if (this.oldestChild.me.isYounger(child)) {
						this.oldestChild.addSibiling(child);
						this.oldestChild = oldestChild.getOlderSibiling();
					} else {
						this.oldestChild.addSibiling(child);
					}

				}
			}
		}
	}

	public int numberOfSibilings() {
		int sibilings = 0;
		TreeNode current = this;
		while (current.getOlderSibiling() != null) {
			sibilings++;
			current = current.getOlderSibiling();
		}
		current = this;
		while (current.getYoungerSibiling() != null) {
			sibilings++;
			current = current.getYoungerSibiling();
		}

		return sibilings;
	}

	public int numberOfChildren() {
		if (oldestChild == null) {
			return 0;
		} else {
			return oldestChild.numberOfSibilings() + 1;
		}
	}

	public TreeNode getOldestSibiling() {
		TreeNode current = this;
		while (current.getOlderSibiling() != null) {
			current = current.getOlderSibiling();
		}
		return current;
	}

	public TreeNode getYoungestSibiling() {
		TreeNode current = this;
		while (current.getYoungerSibiling() != null) {
			current = current.getYoungerSibiling();
		}
		return current;
	}

	public TreeNode getChildNode(Person child) {
		TreeNode current = this.getOldestChild();
		if (current == null) {
			return null;
		}
		while (current.getMe() != child) {
			current = current.getYoungerSibiling();
			if (current == null) {
				return null;
			}
		}
		return current;
	}

	public TreeNode getSibilingNode(Person sibiling) {
		TreeNode current = this;
		if (this.me.isYounger(sibiling)) {
			while (current.me != sibiling) {
				current = current.getOlderSibiling();
				if (current == null) {
					System.out.println("Ova osoba vam nije brat/sestra");
					return null;
				}
			}
			return current;
		} else {
			while (current.me != sibiling) {
				current = current.getYoungerSibiling();
				if (current == null) {
					System.out.println("Ova osoba vam nije brat/sestra");
					return null;
				}
			}
			return current;
		}
	}

	public void delete() {
		this.olderSibiling = null;
		this.youngerSibiling = null;
		this.oldestChild = null;
	}

	public String toString() {
		String odgovor = "Ja sam:" + me;
		if (olderSibiling != null) {
			odgovor += "  Stariji brat: " + olderSibiling.me.toString();
		}
		if (oldestChild != null) {
			odgovor += "  Najstariji sin: " + oldestChild.me.toString();
		}
		if (youngerSibiling != null) {
			odgovor += "  Maldji brat: " + youngerSibiling.me.toString();
		}

		return odgovor;
	}

}
