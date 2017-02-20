package za.co.reverside.service;

public class Tester {
	
	public static void main(String[]args){
		//System.out.println(new Tester().getClass().getResource("/reports/polygon-logo.jpg").toString());
                list("Thabo", 1,3, 4,7);
	}
	
        public static void list(String name, int... num){
             System.out.println(name+ " " + num);
        }
}
