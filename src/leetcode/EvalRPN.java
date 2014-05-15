package leetcode;
 
import java.util.Stack;
 
/**
 * Solution: Use stack store numbers and calculate when meets operator.(get number from stack and store result into stack)
 * Use string.contains(substr) to check operator
 * Don't forget break!!!
 * @author jeffwan
 * @date Apr 1, 2014
 */
public class EvalRPN {
	
    public int evalRPN1(String[] tokens) {
        if(tokens ==null || tokens.length ==0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        String tokenStr = "+-*/";
        for(int i =0;i<tokens.length;i++){
            String t = tokens[i];
            if(tokenStr.contains(t)){
                int x = stack.pop();
                int y = stack.pop();
            switch(t.charAt(0)){
                case '+':
                    stack.push(x+y);
                    break;
                case '-':
                    stack.push(x-y);
                    break;
                case '*':
                    stack.push(x*y);
                    break;
                case '/':
                    stack.push(x/y);
                    break;
                default:

            
            }
            
            
            
            }
            stack.push(Integer.valueOf(t));
            
        }
        return stack.pop();
    }

	public int evalRPN(String[] tokens) {
		if (tokens == null || tokens.length == 0) {
			return 0;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		String operators = "+-*/";
		for (String token : tokens) {
			if (!operators.contains(token)) {
				stack.push(Integer.parseInt(token));
				continue;
			} 
			
			int a = stack.pop();
			int b = stack.pop();
			switch(token.charAt(0)) {
				case '+':
					stack.push(b + a);
					break;
				case '-':
					stack.push(b - a);
					break;
				case '*':
					stack.push(b * a);
					break;
				case '/':
					stack.push(b / a);
					break;
			}
		}
		return stack.pop();	
	}
 
	
	// My ways is not very clear
	public int evalRPN2(String[] tokens) {
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < tokens.length; i++) {
			System.out.println(stack);
			if (isNumberic(tokens[i])) {
				stack.push(tokens[i]);
			} else {
				int number1 = Integer.parseInt(stack.pop());
				int number2 = Integer.parseInt(stack.pop());
				// deal with the operator
				int result = calculate(number2, number1, tokens[i]);
				stack.push(Integer.toString(result));
			}
		}
		
		return Integer.parseInt(stack.peek());
	}
 
	public int calculate(int number1, int number2, String operator) {
		switch (operator.charAt(0)) {
		case '+':
			return number1 + number2;
		case '-':
			return number1 - number2;
		case '*':
			return number1 * number2;
		case '/':
			return number1 / number2;
		default:
			return 0;
		}
	}
 
	public boolean isNumberic(String number) {
		return number.matches("-?\\d+(\\.\\d+)?");
	}
}