import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Male a = new Male("A", "Ristic", new Date(1, 1, 2000), 1);
		Male b = new Male("B", "Ristic", new Date(2, 2, 2020), 2);
		Male c = new Male("C", "Ristic", new Date(3, 3, 2040), 3);
		Male d = new Male("D", "Ristic", new Date(5, 5, 2060), 4);
		Male e = new Male("E", "Ristic", new Date(8, 8, 2080), 5);
		Male f = new Male("F", "Ristic", new Date(9, 9, 2100), 6);
		Male o = new Male("0", "Ristic", new Date(9, 9, 2102), 14);
		Male g = new Male("G", "Ristic", new Date(9, 9, 2120), 7);
		Male h = new Male("H", "Ristic", new Date(9, 9, 2140), 8);
		Male i = new Male("I", "Ristic", new Date(9, 9, 2160), 9);
		Male j = new Male("J", "Ristic", new Date(9, 9, 2180), 10);
		Male k = new Male("K", "Ristic", new Date(9, 9, 2200), 11);
		Male l = new Male("L", "Ristic", new Date(9, 9, 2220), 12);
		Male m = new Male("M", "Ristic", new Date(9, 9, 2240), 13);
		Female n = new Female("N", "Bistic", new Date(9, 9, 2050), 15);
		Female x = new Female("X", "Bistic", new Date(9, 9, 2320), 16);
		Female y = new Female("Y", "Bistic", new Date(9, 9, 2322), 17);
		Female z = new Female("Z", "Bistic", new Date(9, 9, 2520), 18);
		Female t = new Female("T", "Bistic", new Date(9, 9, 2522), 19);

		ArrayList<Person> ljudi = new ArrayList<Person>();
		ArrayList<Integer> deca = new ArrayList<Integer>();
		ArrayList<Person> ljudi1 = new ArrayList<Person>();
		ArrayList<Integer> deca1 = new ArrayList<Integer>();

		ljudi1.add(n);
		deca1.add(0);
		// Ovo je za gornje stablo
		ljudi.add(a);
		ljudi.add(b);
		ljudi.add(c);
		ljudi.add(d);
		ljudi.add(e);
		ljudi.add(f);
		ljudi.add(o);
		ljudi.add(g);
		ljudi.add(h);
		ljudi.add(i);
		ljudi.add(j);
		ljudi.add(k);
		ljudi.add(l);
		ljudi.add(m);
		deca.add(4);
		deca.add(2);
		deca.add(0);
		deca.add(1);
		deca.add(1);
		deca.add(2);
		deca.add(0);
		deca.add(0);
		deca.add(2);
		deca.add(0);
		deca.add(1);
		deca.add(0);
		deca.add(0);
		deca.add(0);
		FamilyTree stablo = new FamilyTree(deca, ljudi, "Ristic");
		FamilyTree stablo2 = new FamilyTree(deca1, ljudi1, "Bistic");
		stablo2.addChild(n, x);
		stablo2.addChild(n, y);
		stablo2.addChild(x, z);
		stablo2.addChild(y, t);
		FamilyTree stablo3 = new FamilyTree(stablo2);
		System.out.println(stablo2);
	}
}
