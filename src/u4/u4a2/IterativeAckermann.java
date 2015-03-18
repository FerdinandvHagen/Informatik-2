package u4.u4a2;

import u4.u4a1.Stack;

public class IterativeAckermann {

    Stack stack = new Stack(10000);

    /**
     * Stack-based iterative implementation of the Ackermann function.
     *
     * @param n parameter n
     * @param m parameter m
     * @return Ackermann(n, m)
     */
    public int A(int n, int m) {
        stack.push(m);
        stack.push(n);
        iterate();
        assert (stack.size() == 1);

        return stack.pop();
    }

    private void iterate() {
        // get both values from stack
        // Top value is first on, next value is second so its like A(a, b)
        int a = stack.pop();                                    //iload_1

        //Case A(0,m)
        if (a == 0) {                                           //ifne 8  -> if(a != 0) goto 38
            int b = stack.pop();                                //iload_2
            stack.push(b + 1);    // return m+1;                //iconst_1   iadd
            return;                                             //ireturn
        }

        //Case A(n+1, 0)
        int b = stack.pop();                                    //iload2
        if (b == 0) {                                           //ifne 21 -> if(b!=0) goto 47
            stack.push(1);      //A(n,1)                        //aload 0 -> save one instance
            stack.push(a - 1);    //so it's A(a-1, 1)           //iload_1 i_const 1 isub iconst_1
            iterate();                                          //invokevirtual #2 -> A
            return;                                             //ireturn
        }

        //Case A(n+1, m+1)                                      //a_load 0
        stack.push(b - 1);        //First calculate A(n+1, m)   //iload_1 iconst_1 isub aload_0
        stack.push(a);                                          //iload_1 iload_2 iconst_1 isub
        iterate();                                              //invokevirtual #1
        stack.push(a - 1);        //Store a-1                   //iload_1 iconst_1 isub aload_0
        iterate();                                              //invokevirtual
        return;                                                 //ireturn
    }
}