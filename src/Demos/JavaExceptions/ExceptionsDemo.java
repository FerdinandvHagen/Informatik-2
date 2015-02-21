package Demos.JavaExceptions;

class ExceptionsDemo {

	public static void main (String [] args) {
		System.out.println("main wird ausgeführt!");
		method1();
	}

	static void method1() {
		System.out.println("Anfang von method1...");
		method2();
		System.out.println("...Ende von method1!");
	}

	static void method2() {
		System.out.println("Anfang von method2...");

		int x = 10;
		int y = 0;
		double z = 0.0;

		try {
			z = x / y;
		} catch (ArithmeticException e) {
			System.out.println("Durch null dividiert...");
		}
	
		System.out.println(z);
		System.out.println("...Ende von method2!");
	}
}
