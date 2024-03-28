
public class LinkedNumber {
	private int base;
	private DLNode<Digit> front; // Considered the head of the DLL
	private DLNode<Digit> rear; //Considered the tail of the DLL
	
	
	
	public LinkedNumber(String num, int baseNum) { //Constructor
		this.base = baseNum;
		
		if (num.isEmpty()){ // Checks if the variable num is an empty string
			throw new LinkedNumberException("no digits given");			
		}
		
		front = null;
		rear = null;
		
		DLNode<Digit> prev = null;
		
		// Use for loop to convert the strings in the array into integers
		char[] nums = num.toCharArray();
		
		for (char chars : nums) { //Converts each character in the num string into a collection of digits.
			Digit digit = new Digit(chars);
			
			DLNode<Digit> newNode = new DLNode<>(digit);
			
			if (prev == null) { // Meaning the pointer has not been set to anything, then set the head of the LL to equal the first node.
				front = newNode;
				rear = newNode;
			}
			
			if (prev != null) { // Connects the nodes together, if there exists a node.
				prev.setNext(newNode); 
				newNode.setPrev(prev); 
			}
			rear = newNode; //Once the Linked list is established set the tail = to the last node.
			prev = newNode; // Update previous and set the pointer equal to the newly established node. 
			
		}		
	}

	
	public LinkedNumber(int num) {
		this.base = 10;
		
		if (num < 0) {
			throw new LinkedNumberException("cannot convert invalid number");
		}
		String stringNums = Integer.toString(num);
		// Convert the sequence of integers into a string
		
		
		// Take every character in the sequence of strings and turn it into an array
		char [] nums = stringNums.toCharArray();
		
		front = null;
		rear = null;
		
		DLNode<Digit> prev = null;
		
		for (char chars: nums) {
			Digit digit = new Digit(chars);//Change every item in the array into a object of type digit.
			
			//Make nodes for the DLL using the digits
			DLNode<Digit> newNode = new DLNode<>(digit);
			
			if (prev == null) {
				front = newNode;
				rear = newNode;
			}
			if (prev != null) {
				prev.setNext(newNode);
				newNode.setPrev(prev);
			}
			
			rear = newNode; //Makes sure that the tail is going to be the last item on the list.
			prev = newNode; //Updates the pointer for the previous node.
			
		}

	}
	
	public boolean isValidNumber() {
		
		// Use a temporary variable to access the linked list
		DLNode<Digit> temp = front;
		
		
		
		while (temp != null) {
			
			if (temp.getElement().getValue() < 0 || temp.getElement().getValue() >= base) { //If the value satifies the criteria, then it is a valid number
				return false;
			}
			temp = temp.getNext();
		}
		
		return true;
	}
	
	public int getBase() {
		return base;
	}
	
	public DLNode<Digit> getFront() {
		return front;
	}
	
	public DLNode<Digit> getRear() {
		return rear;
	}
	
	public int getNumDigits() {
		DLNode<Digit> temp = front;
		int length = 0;
		while (temp != null) { // Condition breaks/exits once we reach the end of the DLL.
			temp = temp.getNext(); // Iterate through the nodes
			length++; // Increment length by one through each node we pass by.
		}
		
		return length;
	}
	
	public String toString() {
		
		DLNode<Digit> temp = front;
		
		String digits = "";
		
		while (temp!= null) {	
			digits = digits + temp.getElement();
			temp = temp.getNext();
			
		}
		
		return digits;
	}
	
	public boolean equals(LinkedNumber other) {
		if (this.base == other.base && this.toString().equals(other.toString())) { 
			//First we compare the bases of the two and then order of the strings, by comparing the string representation of the two.
			//to determine if they are equal or not
			return true;
		}
		else { // If not, they are not equal
			return false;
		}
		
	}
	
	/*
	 * This method is responsible for converting a system of numbers to another system whether that being decimal to non decimal, non to decimal, and non-decimal to non-decimal
	 * @param newBase, is the new base of the system you are trying to convert.
	 */
	public LinkedNumber convert(int newBase) {
		
		if (isValidNumber() != true) {
			throw new LinkedNumberException("cannot convert invalid number");
		}
		
		if (getBase() == 10) { // Conversion from decimal number system to non-decimal number system
			LinkedNumber case1 = decimalToNon(newBase);
			return case1;
		}else if (getBase() != 10 && newBase == 10) { //Conversion from non-decimal number system to decimal number system
			LinkedNumber case2 = nonToDecimal();
			return case2;
		}else { // Conversion from non-decimal number system to another non-decimal number system
			LinkedNumber case3 = nonToNon(newBase); 
			return case3;
		}
	}
	
	
	
	private LinkedNumber decimalToNon(int newBase) { //Private helper method that converts a decimal number system to a non-decimal number system
		
		DLNode<Digit> temp = rear;
		int index = 0;
		int val = 0;
		while (temp != null) { // Iterate through the DLL using a while loop from rear to front
			val += temp.getElement().getValue() * Math.pow(10, index); //Sum all the products of val using the index as an exponent and 10 as a base.
			temp = temp.getPrev(); // Get the previous node in each iteration
			index++; // Keeps track of the index
		}
		
		String remainders = "";
		while (val != 0) {
			int remainder = (int)(val % newBase);
			
			if (remainder < 10) { //If base is less than 10 it is a decimal #.
				remainders = remainder + remainders;
				
			} else if(remainder > 9) { //If base is greater than 10 than it is hexadecimal.
				if (remainder == 10) {
					remainders = 'A' + remainders;
				} if(remainder == 11) {
					remainders = 'B' + remainders;
				}if (remainder == 12) {
					remainders = 'C' + remainders;
				}if (remainder == 13) {
					remainders = 'D' + remainders;	
				}if (remainder == 14) {
					remainders = 'E' + remainders;
				}if (remainder == 15) {
					remainders = 'F' + remainders;
				}
			}
			val = val/newBase;
		}
		remainders.toString();

		LinkedNumber caseOneDLL = new LinkedNumber(remainders, newBase);
		return caseOneDLL;
	}

	private LinkedNumber nonToDecimal() { //Private helper method that converts a non-decimal number system to a decimal number system.
		int val = 0;
		int index = 0; //Keeps track of the index.
		DLNode<Digit> temp = rear;
		
		while (temp != null) {
			val += temp.getElement().getValue() * Math.pow(getBase(), index); //Sum all the products in val using the base the system is and the index.
			temp = temp.getPrev(); //Traverse through the linked list starting at the end.
			index++;
		}

		LinkedNumber caseTwoDLL = new LinkedNumber(String.valueOf(val), 10);		
		return caseTwoDLL;
		
	}
	
	private LinkedNumber nonToNon(int newBase) {
		
		LinkedNumber myDLL = nonToDecimal();

		myDLL = myDLL.decimalToNon(newBase);
		
		return myDLL;
	}
	
	/**
	 * This method is responsible for adding a Digit node with a specified value at a specified index
	 * @param digit, position digit is the value of the Digit node, whilst Position is the specific index. 
	 */
	public void addDigit(Digit digit, int position) {
		DLNode <Digit> newNode = new DLNode<>(digit);
		DLNode <Digit> temp = rear; //Create a temporary pointer to the beginning of the DLL
		
		int length = getNumDigits(); // This retrieves the length of the DLL
		
		if (position < 0 || position > length) { //If the position is less than 0 or greater than length, then it is out of bounds
			throw new LinkedNumberException("invalid position");
		}
		if (position == 0) { //If position == 0 then simply add the node to the first index of the DLL which in this case starts at rear.
			if (rear != null) {
				newNode.setPrev(rear);
				rear.setNext(newNode);
			}
			rear = newNode;
			if (front == null) { // This sets front to the newNode if front is empty
				front = newNode;
			}
			
			// If the position is equal to the length of the list then simply append the node to end of the DLL, which in this case is this front
		} else if (position == length) { 
			
			newNode.setNext(front);
			
			if (front != null) {
				front.setPrev(newNode);
			}
			front = newNode;
			if (rear == null) {
				rear = newNode;
			}
			
		} else { //Insertion of node in between 0 and n+1.
			
			//Find the position in which you trying to add the node by iterating through the list, using the position as the condition starting at the rear
			for (int i = 0; i < position; i++) {
				temp = temp.getPrev();
			}
			
			//Use two pointers before and after so we can adjust the pointers of the nodes later on to add the newNode.
			DLNode<Digit> before = temp; 
			DLNode<Digit> after = temp.getNext();
			
			//Adjust pointers to add the newNode
			before.setNext(newNode);
			newNode.setPrev(before);
			
			after.setPrev(newNode);
			newNode.setNext(after);		
			}		
		}
	
	/**
	 * This method removes a digit at the specified position.
	 * @param position, position is the index we would like to remove digit from the DLL
	 */
	public int removeDigit(int position) {
		int length = getNumDigits();
		if (position < 0 || position > length) { //Throws an exception of position is out of bounds
			throw new LinkedNumberException("invalid position");
		}
		DLNode<Digit> temp = rear; //Create temporary node
		
		if (position == 0) { //If the position is at 0, then remove the digit in the beginning which in this case is at the rear.
			rear = temp.getPrev();
			temp.setPrev(null);
			rear.setNext(null);

	
		}else if (position == length - 1) { //If the position is equal to the length removes the Node at the end of the list which in this case is front
			temp = front;
			front = temp.getNext();
			temp.setNext(null);
			front.setPrev(null);
			
		}else { //Insertion of node in between rear and front
			for (int i = 0; i < position; i++) { //Itearte through the linked list to get to the end setting temp = to the position # in the DLL if it is valid
				temp = temp.getPrev();
			}
			
			
			DLNode<Digit> before = temp.getPrev(); //Points to the node before the position
			DLNode<Digit> after = temp.getNext(); // Points to the node after temp
			
			temp.setPrev(null); //Re-adjust temp nodes to remove the temp node
			temp.setNext(null);
			
			before.setNext(after); //Adjust pointers for before and after nodes.
			after.setPrev(before);
	
			}
		int digitValue = 0;
		
		// Obtains the value of that node as part of the whole number.
		if (base == 10) { 
			digitValue += temp.getElement().getValue() * Math.pow(10, position);
		}else {
			digitValue += temp.getElement().getValue() * Math.pow(base, position);
		}
		return digitValue;

		
	}
}
