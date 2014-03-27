
public class BinarySearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intA = {4,8};
		try {			binarySearch(intA,9);

			binarySearch(intA,3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static int binarySearch(int[] array, int target) throws Exception {
		return binarySearch(array, target, 0, array.length-1);
		}
	static 	int binarySearch( int[] array, int target, int lower,
		int upper ) throws Exception {
		int center, range;
		range = upper - lower;
		if( range < 0 ){ //case when there is only two elements left
//		throw new Exception("Limits reversed");
			throw new Exception("Element not in array");

		} else if( range == 0 && array[lower] != target ){
		throw new Exception("Element not in array");
		}
		if( array[lower] > array[upper] ){
		throw new Exception("Array not sorted");
		}
		center = ((range)/2) + lower;
		if( target == array[center] ){
		return center;
		} else if( target < array[center] ){
		return binarySearch( array, target, lower, center - 1 );
		} else {
		return binarySearch( array, target, center + 1, upper );
		}
		}
}
