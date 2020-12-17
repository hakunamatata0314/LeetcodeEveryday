// 772. Basic Calculator III/h

// recursion
class Solution {
    int index = -1;
    public int calculate(String s) {
        int ans = 0;
        int bef = 0;
        char op = '+';
        int num = 0;
        
        while(index < s.length() - 1) {
            char c = s.charAt(++index);
            if (c == '(') {
                num = calculate(s);
            }
            
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }
            
            if (index == s.length() - 1 || c == '+' || c == '-' || c == '*' || c == '/' || c == ')') {
                if (op == '+') {
                    ans += bef;
                    bef = num;
                } else if (op == '-') {
                    ans += bef;
                    bef = -num;
                } else if (op == '*') {
                    bef *= num;
                } else if (op == '/') {
                    bef /= num;
                }
                num = 0;
                op = c;
            }
            
            if (c == ')') break;
        }
        return ans + bef;
    }
}

// iteration
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>(); // the stack that stores numbers
        Stack<Character> ops = new Stack<>(); // the stack that stores operators (including parentheses)
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                // iteratively calculate each number
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                nums.push(num);
                num = 0; // reset the number to 0 before next calculation
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // do the math when we encounter a ')' until '('
                while (ops.peek() != '(') nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                ops.pop(); // get rid of '(' in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(c, ops.peek())) nums.push(operation(ops.pop(), nums.pop(),nums.pop()));
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private static int operation(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // assume b is not 0
        }
        return 0;
    }
    // helper function to check precedence of current operator and the uppermost operator in the ops stack 
    private static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }
    
}