
public class ReverseWordsInString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverseWords1(" b  a "));
	}
    public static String reverseWords1(String s) {
        if(s==null ) return s;
        s=s.trim();
        if(s.length()==0||s.length()==1) return s;
        String[] array = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i=array.length-1; i>0;i--){
               sb.append(array[i]).append(" ");
        }
        sb.append(array[0]);
        return sb.toString();
        }
}
