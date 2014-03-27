import java.util.Arrays;

/*
 * 
 ProbLem  Write a function that reverses the order of the words in a string. For
example, your function should transform the string "Do or do not, there is no try." to "try. no is 
there not, do or Do". Assume  that all words are space delim- ited and treat punctuation the same 
as letters.

 */
public class ReverseWordsInPlace {

	static void  wcReverseWords( char  str[] ){
		int start =  0, end   =  0, length;
		length =  str.length;
		/* Reverse entire string */ wcReverseString(str, start, length - 1); while( end   <  length ){
			if( str[end] !=  ' ' ){  /*  Skip non-word characters */
				/* Save   position  of beginning of word  */
				start =  end;
				/* Scan   to  next  non-word character */
				while( end   <  length &&   str[end] !=  ' ' ) {
					end++;
				}
				/* Back  up  to end   of word  */
				end--;
				/* Reverse word  */
				wcReverseString( str, start, end   );
			}
			end++; /*  Advance to next token */
		}
	}

	static void wcReverseString(  char str[], int start, int end   ){
		char temp;
		while( end   >  start  ){
			/*  Exchange characters */ temp   =  str[start]; str[start] =  str[end]; str[end] =  temp;
			/* Move indices  towards middle */
			start++; end--;
		}
	}
	public static void main(String[] args) {
		String test = "This is a test string";
		char[] charA = test.toCharArray();
		wcReverseWords(charA);
		System.out.println("result: ");
		System.out.println(Arrays.toString(charA));
	}

}
