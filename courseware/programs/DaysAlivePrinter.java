public class DaysAlivePrinter
{
	public static void main(String[] args)
	{
		Day birthday = new Day(1951, 5, 25);
		Day lastDay = new Day(2012, 7 ,23);
		int daysAlive = lastDay.daysFrom(birthday);
		System.out.println(daysAlive);
	}
}