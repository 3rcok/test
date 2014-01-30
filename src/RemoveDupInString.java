
public class RemoveDupInString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String nonCont= "abababb";
		char[] str = nonCont.toCharArray();
		removeDuplicatesEff(str);
		System.out.println(nonCont + " to " + new String(str));
		char[] str1 = nonCont.toCharArray();

		removeDuplicates(str1);
		System.out.println(nonCont + " to " + new String(str1));

	}
	static void removeDuplicates(char[] str) {
		if (str == null) return;
		int len = str.length;
		if (len < 2) return;
		int tail = 1;
		for (int i = 1; i < len; ++i) {
		int j;
		for (j = 0; j < tail; ++j) {
		if (str[i] == str[j]) break;
		}
		if (j == tail) {
		str[tail] = str[i];
		++tail;
		}
		}
		str[tail] = 0;
		}

	public static void removeDuplicatesEff(char[] str) {
	if (str == null) return;
	int len = str.length;
	if (len < 2) return;
	boolean[] hit = new boolean[256];
	for (int i = 0; i < 256; ++i) {
	hit[i] = false;
	}
	hit[str[0]] = true;
	int tail = 1;
	for (int i = 1; i < len; ++i) {
		if (!hit[str[i]]) {
		str[tail] = str[i];
		++tail;
		hit[str[i]] = true;
		}
	}
	str[tail] = 0;
}
}
