package Demos.JavaHelloMachine;

/**
 *  Demo: static void main, Argumente, Objekte, Objektmethoden, printout
 */
public class HelloMachine {

	public static void main(String [] args) {
		String userName = args[0];

		HelloMachine myHelloMachine = new HelloMachine();

		myHelloMachine.sayHello(userName);
	}

	public void sayHello(String name) {
		System.out.println("Hello, " + name + "!");
	}

}
