
public class Main {

	public static void main(String[] args) {
		String str = "qoymlNlpY :ccdf lpy yzJ .qoh ln lxigqoh qlxlm eeiw zot ydpy gmipylnoC ,zot gmiyqdncyzo ho ydpy ci lniqk tN .lsie sooe tlpy ydpw yom ,smipy amd tdc tlpy ydpw tj lefolf gmigazb ho ydpy ci lniqk tN .tyicoiqzk ho ydpy ci lniqk tN .edminiqk d nd i clT";
		
		char[] array = str.toCharArray();
		int length = array.length;
		for(int i=0;i<length/2;i++) {
			char temp = array[i];
			array[i] = array[length - i - 1];
			array[length-i-1] = temp;
		}
		
		for(int i=0;i<length;i++)
			System.out.print(array[i]);
	}

}
