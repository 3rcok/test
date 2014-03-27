import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class SegmentString {
public static HashMap<String, String> map= new HashMap();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word = "hellowtoyou";
		String[] arr = {"hellow","to","you", "he", "hell", "toy"};
		Set<String> strSet = new HashSet(Arrays.asList(arr));
				
		System.out.println( word + " was segmented to " + segmentString(word, strSet));

	}
	
	public static String segmentString(String input, Set<String> dic) {
		if (dic.contains(input)) return input;
		for(int i=0;i<=input.length();i++) {
			String prefix = input.substring(0,i);
			if(dic.contains(prefix)) {
//				if(i==(input.length())) {
//					System.out.println(" return " + prefix);
//					return prefix;
//				}
				if(map.get(input)!=null) return map.get(input);
				String suffix = segmentString(input.substring(i), dic);
				if(suffix!=null) {
					System.out.println("input " + input + " value: " + prefix + " " + suffix);
					map.put(input, prefix + " " + suffix);
					return prefix + " " + suffix;
				}
			}
		}
		return null;
	}

}
