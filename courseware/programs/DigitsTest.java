/**
 * Complete the code in this method 
 * Define last and assign the last digit of the number to it
 */
class Digits
{
    /**
     * Gets the last digit of a number
     * @param number the number whose last digit we want
     * @return the last digit of the parameter
     */
    
    public int lastDigit(int number)
    {
        
        String str= number+"";
       int len=str.length();
       int last=Integer.parseInt(str.charAt(len-1)+"");
       return last;
    }
}

public class DigitsTest{
	public static void main(String args[]){
		Digits d=new Digits();
		int score=0;
		System.out.println("Arguments\tActual\tExpected");
		System.out.println("---------------------------------------------");
		int testcases[]={10231,1,345,5};
		for(int i=0;i<testcases.length-1;i+=2){
			int last=d.lastDigit(testcases[i]);
			String teststatus="";
			if(last==testcases[i+1]){
				teststatus="pass";
				score ++;
			}
			else{
				teststatus="fail";
			}
			System.out.println(testcases[i]+"\t"+last+"\t"+testcases[i+1]+"\t"+teststatus);

		}
		System.out.println("Score: "+score);
	}
}