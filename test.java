
public class test {

	public static void main(String[] args) {
//		LinkedNumber ln1 = new LinkedNumber(255);
		LinkedNumber ln2 = new LinkedNumber("7492", 10);
//		LinkedNumber ln3 = new LinkedNumber("13772053", 8);
		System.out.println(ln2.getNumDigits());
//		ln2.addDigit(new Digit('A'), 0); // Assuming this adds to the list if it's empty
//        ln2.addDigit(new Digit('L'), 0); // Add '2' after '1'
     // Add '3' after '2'

        
        System.out.println(ln2.removeDigit(3));

		


	}

}
