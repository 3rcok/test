import java.util.ArrayList;


public class GetAllSubSets {
	static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set,
			int index) {
		ArrayList<ArrayList<Integer>> allsubsets;
		if (set.size() == index) {
			allsubsets = new ArrayList<ArrayList<Integer>>();
			allsubsets.add(new ArrayList<Integer>()); // Empty set
		} else {
			allsubsets = getSubsets(set, index + 1);
			int item = set.get(index);
			ArrayList<ArrayList<Integer>> moresubsets =
					new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> subset : allsubsets) {
				ArrayList<Integer> newsubset = new ArrayList<Integer>();
				newsubset.addAll(subset); //
				newsubset.add(item);
				moresubsets.add(newsubset);
			}
			allsubsets.addAll(moresubsets);
		}
		return allsubsets;
	}
public static void main(String[] args) {
	ArrayList<Integer> o_set = new ArrayList<Integer>();
	o_set.add(new Integer("1"));
	o_set.add(new Integer("2"));
	o_set.add(new Integer("4"));
	ArrayList<ArrayList<Integer>> all = getSubsets(o_set,1);
	System.out.println(all);
	}
}
