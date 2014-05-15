package leetcode;

public class UniquePaths {
//	http://leetcode.com/2010/11/unique-paths.html
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(uniquePaths(6,2));
		System.out.println(dp(6,2));
		System.out.println(bt(6,2));

	}
	/*
    public static int uniquePaths1(int m, int n) {
        if(m==1)
        return 1;
        if(n==1)
        return 1;
        if(m==1 && n==1){
            return 0;
        }
        return uniquePaths1(m-1,n) + uniquePaths1(m,n-1);
    }
    */
  /*  
    public static int uniquePaths(int m, int n) {
    	return backtrack(1,1,m,n);
    }
    static int backtrack(int r, int c, int m, int n) {
    	  if (r == m && c == n)
    	    return 1;
    	  if (r > m || c > n)
    	    return 0;
    	 
    	  return backtrack(r+1, c, m, n) + backtrack(r, c+1, m, n);
    	}
    */
    static int M_MAX = 100;
    static int N_MAX = 100;
    static  int[][] mat = new int[M_MAX+2][N_MAX+2];
    static {
	  for (int i = 0; i < M_MAX+2; i++) {
	    for (int j = 0; j < N_MAX+2; j++) {
	      mat[i][j] = -1;
	    }
	  }
    }
   static int backtrack(int r, int c, int m, int n, int[][] mat) {
    	  if (r == m && c == n)
    	    return 1;
    	  if (r > m || c > n)
    	    return 0;
    	 
    	  if (mat[r+1][c] == -1)
    	    mat[r+1][c] = backtrack(r+1, c, m, n, mat);
    	  if (mat[r][c+1] == -1)
    	    mat[r][c+1] = backtrack(r, c+1, m, n, mat);
    	 
    	  return mat[r+1][c] + mat[r][c+1];
    	}
    	 
    static	int bt(int m, int n) {

    	  return backtrack(1, 1, m, n, mat);
    	}
    	
    
    static int dp(int m, int n) {
//      int mat[M_MAX+2][N_MAX+2] = {0};
    	  int[][] mat = new int[M_MAX+2][N_MAX+2];
    	  for (int i = 0; i < M_MAX+2; i++) {
    	    for (int j = 0; j < N_MAX+2; j++) {
    	      mat[i][j] = 0;
    	    }
    	  }
      mat[m][n+1] = 1;
//      mat[m+1][n] = 1;

     
      for (int r = m; r >= 1; r--)
        for (int c = n; c >= 1; c--)
          mat[r][c] = mat[r+1][c] + mat[r][c+1];
     
      return mat[1][1];
    }
    
}
