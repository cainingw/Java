package api;

public class test {

	 public static void main(String[] args)
	 {
	 String[] arr = {"qwe","a","b"};
	 String[] a = arr;
	 int[] b = {1,2,3};
	 int c = 0;
	 for(int i = 0; i< 3; i++){
//		 System.out.println(a[i]);
		 if(b[i] > 0){
			 c += 1;
			 
		 }
	 }
	 System.out.println(b[2]);
	 }

}
