package Demos.JavaOrganisation.demo1;

import Demos.JavaOrganisation.demo2.ExtendedPublic;

public class Public {

	public static void main (String [] args) {
		
		ExtendedPublic eP = new ExtendedPublic();
		eP.foo();

		System.out.println("It works!");
	}

}
