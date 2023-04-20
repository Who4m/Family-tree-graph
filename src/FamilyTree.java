import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FamilyTree {

	private TreeNode root;
	private String FamilyName;

	public FamilyTree(ArrayList<Integer> brojDece, ArrayList<Person> family, String FamilyName) {
		ArrayList<TreeNode> drvo = new ArrayList<TreeNode>();
		if ((brojDece.size() != family.size()) || (checkLastName(family, FamilyName) == false)) {
			System.out.println("Kreiranje stabla nije moguce");
		} else {
			root = new TreeNode(family.get(0));
			this.FamilyName = FamilyName;
			drvo.add(root);
			int velicina = family.size();
			int sledeci = 0;
			int staoSamNa = 1;
			int kolikoDeceIma = brojDece.get(0);
			while (sledeci < velicina) {
				kolikoDeceIma = brojDece.get(sledeci);
				for (int i = 0; i < kolikoDeceIma; i++) {
					drvo.get(sledeci).addChild(family.get(staoSamNa));
					drvo.add(drvo.get(sledeci).getChildNode(family.get(staoSamNa)));
					staoSamNa++;
				}

				sledeci++;
			}

		}

	}

	public FamilyTree(FamilyTree drvo) {
		this.root = new TreeNode(drvo.getRoot());
		TreeNode thisCurrent = this.root;
		TreeNode current = drvo.getRoot();
		Stack<TreeNode> thisStack = new Stack<TreeNode>();
		Stack<TreeNode> currentStack = new Stack<TreeNode>();
		do {
			if ((current.getOldestChild() != null) || (current.getYoungerSibiling() != null)) {
				if ((current.getOldestChild() != null) && (current.getYoungerSibiling() != null)) {
					// Ovaj IF je ako ima i brata i sina
					// ovde dodajem brata
					thisCurrent.addSibiling(current.getYoungerSibiling().getMe());
					thisStack.push(thisCurrent.getYoungerSibiling());
					currentStack.push(current.getYoungerSibiling());
					// ovde dodajem dete
					thisCurrent.addChild(current.getOldestChild().getMe());
					thisCurrent = thisCurrent.getOldestChild();
					current = current.getOldestChild();
				} else if ((current.getOldestChild() != null) && (current.getYoungerSibiling() == null)) {
					// ovaj if je ako ima samo dete a nema brata
					// ovde dodajem sina
					thisCurrent.addChild(current.getOldestChild().getMe());
					thisCurrent = thisCurrent.getOldestChild();
					current = current.getOldestChild();
				} else if ((current.getOldestChild() == null) && (current.getYoungerSibiling() != null)) {
					// ovaj if je ako nema sina a ima brata
					// ovde dodajem brata
					thisCurrent.addSibiling(current.getYoungerSibiling().getMe());
					thisCurrent = thisCurrent.getYoungerSibiling();
					current = current.getYoungerSibiling();
				}
			} else {
				// ovaj else je ako nema ni brata ni sina
				thisCurrent = thisStack.pop();
				current = currentStack.pop();
			}
		} while (!((currentStack.isEmpty())
				&& ((current.getOldestChild() == null) && (current.getYoungerSibiling() == null))));

	}

	public TreeNode lookUpPerent(Person osoba) {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		TreeNode next = root;
		do {
			if (next.getChildNode(osoba) == null) {
				if ((next.getOldestChild() != null) && (next.getYoungerSibiling() != null)) {
					q.add(next.getOldestChild());
					next = next.getYoungerSibiling();
				} else if ((next.getOldestChild() != null) && (next.getYoungerSibiling() == null)) {
					q.add(next.getOldestChild());
					next = q.poll();
				} else if ((next.getOldestChild() == null) && (next.getYoungerSibiling() != null)) {
					next = next.getYoungerSibiling();
				} else {
					next = q.poll();
					if (next == null) {
						return null;
					}
				}
			} else {
				return next;
			}
		} while (!((q.isEmpty()) && ((next.getOldestChild() == null) && (next.getYoungerSibiling() == null))));
		if (next.getMe() == osoba) {
			return next;
		} else {
			return null;
		}
	}

	public boolean addSibiling(Person osoba, Person sibiling) {
		TreeNode a = lookUpPerent(osoba);
		if (lookUpPerent(osoba) == null) {
			return false;
		} else {
			a.addChild(sibiling);
			return true;
		}
	}

	public boolean addChild(Person osoba, Person child) {
		TreeNode a = lookUp(osoba);
		if (lookUp(osoba) == null) {
			return false;
		} else {
			a.addChild(child);
			return true;
		}
	}

	public TreeNode lookUp(Person osoba) {
		Stack<TreeNode> q = new Stack<TreeNode>();
		TreeNode next = root;
		while (next.getMe() != osoba) {
			if ((next.getOldestChild() != null) || (next.getYoungerSibiling() != null)) {
				if ((next.getOldestChild() != null) && (next.getYoungerSibiling() != null)) {
					q.push(next.getYoungerSibiling());
					next = next.getOldestChild();
				} else {
					if (next.getOldestChild() == null) {
						next = next.getYoungerSibiling();
					} else {
						next = next.getOldestChild();
					}
				}
			} else {
				if (q.isEmpty()) {
					return null;
				} else {
					next = q.pop();
				}
			}
		}
		return next;
	}

	public String toString() {
		if (root == null) {
			return "Null";
		}
		Stack<TreeNode> q = new Stack<TreeNode>();
		Stack<Integer> kolenoBrata = new Stack<Integer>();
		String odgovor = "";
		odgovor += root.getMe() + "\n";
		TreeNode next = root.getOldestChild();
		int koleno = 1;
		if (next == null) {
			return odgovor;
		}
		do {
			if ((next.getOldestChild() != null) || (next.getYoungerSibiling() != null)) {
				for (int i = 0; i < koleno; i++) {
					odgovor += "      ";
				}
				odgovor += next.getMe() + "\n";
				if ((next.getOldestChild() != null) && (next.getYoungerSibiling() != null)) {
					q.push(next.getYoungerSibiling());
					kolenoBrata.push(koleno);
					next = next.getOldestChild();
					koleno++;
				} else {
					if (next.getOldestChild() == null) {
						next = next.getYoungerSibiling();
					} else {
						next = next.getOldestChild();
						koleno++;
					}
				}

			} else {
				for (int i = 0; i < koleno; i++) {
					odgovor += "      ";
				}
				odgovor += next.getMe() + "\n";
				koleno = kolenoBrata.pop();
				next = q.pop();

			}

		} while (!((q.isEmpty()) && ((next.getOldestChild() == null) && (next.getYoungerSibiling() == null))));
		for (int i = 0; i < koleno; i++) {
			odgovor += "      ";
		}
		odgovor += next.getMe() + "\n";
		return odgovor;
	}

	public TreeNode getRoot() {
		return root;
	}

	public boolean remove(Person osoba) {
		TreeNode current = lookUp(osoba);
		TreeNode perent = lookUpPerent(osoba);
		if (current == null) {
			return false;
		}
		if (perent == null) {
			root = null;
			current.delete();
			return true;
		}
		if (current.getOlderSibiling() == null) {
			// ako je on najstariji
			perent.setOldestChild(current.getYoungerSibiling());
			current.delete();
			return true;
		} else {
			// ako ima starijeg brata
			current.getOlderSibiling().setYoungerSibiling(current.getYoungerSibiling());
			if (current.getYoungerSibiling() != null) {
				current.getYoungerSibiling().setOlderSibiling(current.getOlderSibiling());
			}
			current.delete();
			return true;
		}
	}

	public void destructor() {
		TreeNode current = root;
		Queue<TreeNode> forDestruction = new LinkedList<TreeNode>();
		Queue<TreeNode> traverse = new LinkedList<TreeNode>();
		do {
			if ((current.getOldestChild() != null) || (current.getYoungerSibiling() != null)) {
				if ((current.getYoungerSibiling() != null) && (current.getOldestChild() != null)) {
					forDestruction.add(current);
					traverse.add(current.getOldestChild());
					current = current.getYoungerSibiling();
				} else if ((current.getOldestChild() == null) && (current.getYoungerSibiling() != null)) {
					forDestruction.add(current);
					current = current.getYoungerSibiling();
				} else if ((current.getOldestChild() != null) && (current.getYoungerSibiling() == null)) {
					forDestruction.add(current);
					traverse.add(current.getOldestChild());
					current = traverse.poll();
				}
			} else {
				forDestruction.add(current);
				current = traverse.poll();
			}
		} while (!((traverse.isEmpty())
				&& ((current.getOldestChild() == null) && (current.getYoungerSibiling() == null))));

		forDestruction.add(current);
		while (!forDestruction.isEmpty()) {
			forDestruction.poll().delete();
		}
		root = null;
	}

	public static int numberOfMarriages(FamilyTree tree1, FamilyTree tree2) {
		int numberOfMarriages = 0;
		Person spaus;
		TreeNode current = tree1.getRoot();
		Queue<TreeNode> traverse = new LinkedList<TreeNode>();
		do {
			spaus = current.getMe().getSpouse();
			if (tree2.lookUp(spaus) != null) {
				numberOfMarriages++;
			}
			if ((current.getOldestChild() != null) || (current.getYoungerSibiling() != null)) {
				if ((current.getYoungerSibiling() != null) && (current.getOldestChild() != null)) {
					traverse.add(current.getOldestChild());
					current = current.getYoungerSibiling();
				} else if ((current.getOldestChild() == null) && (current.getYoungerSibiling() != null)) {

					current = current.getYoungerSibiling();
				} else if ((current.getOldestChild() != null) && (current.getYoungerSibiling() == null)) {
					traverse.add(current.getOldestChild());
					current = traverse.poll();
				}
			} else {
				current = traverse.poll();
			}

		} while (!((traverse.isEmpty())
				&& ((current.getOldestChild() == null) && (current.getYoungerSibiling() == null))));
		spaus = current.getMe().getSpouse();
		if (tree2.lookUp(spaus) != null) {
			numberOfMarriages++;
		}
		return numberOfMarriages;
	}

	private boolean checkLastName(ArrayList<Person> family, String FamilyName) {

		for (int i = 0; i < family.size(); i++) {
			if (!family.get(i).getLastName().equals(FamilyName)) {
				return false;
			}
		}
		return true;
	}

}
