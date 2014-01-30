import java.util.TreeMap;


public class StringTest {
	public static void main(String[] args) {
		String serverType = "type";
		String region = "region";
		String nullStr = null;
		System.out.println(nullStr + " i");
		String condClause = "where isBackup=\"false\" and allowAutoProvisioning=\"true\" and maxNumOfIpbxes > 0 and serverType =\""+serverType+ "\" and region =\""+region+ "\" order by weight asc";
				System.out.println("condC " + condClause);
				
		//use an object as key in treemap, it throws ClassCastException at runtime if it doesn't implement Comparable		
		TreeMap<Pair, String> tm = new TreeMap<Pair, String>();
		tm.put(new Pair(1,1), "pair1");
		tm.put(new Pair(2,2), "pair1");
		tm.put(new Pair(5,5), "pair1");
		tm.put(new Pair(4,4), "pair1");
		System.out.println(tm);

	}
	static class Pair{
		private int x =0;
		private int y=0;
		public Pair(int x, int y) {
			this.x = x;
			this.y=y;
		}
	}
	
	String constructSQL(String region, String serverType) {
		String condClause = null;
		if (region == null || region.trim().length()==0) {
			String defaultRegion = System.getProperty("region");
			if(defaultRegion != null && defaultRegion.trim().length()!=0) {
				region = defaultRegion.trim();
			} 
		}
		
		if(region != null && region.trim().length()!=0){
			condClause = "where isBackup=\"false\" and allowAutoProvisioning=\"true\" and maxNumOfIpbxes > 0 and serverType =\""+serverType+ "\" and region =\""+region+ "\" order by weight asc";
		} else {
			condClause = "where isBackup=\"false\" and allowAutoProvisioning=\"true\" and maxNumOfIpbxes > 0 and serverType =\""+serverType+ "\" and region is null order by weight asc";
		}
		System.out.println("condClause: "+condClause);
		return condClause;
	}

}
