// BlueJ project: lesson3/friends2
// Video: Implementing Get Friends Method
class Person
{
    private String name;
    private String friends;

    public Person(String aName)
    {
        name = aName;
        friends = "";
    }

   // This method returns the friends of this Person object
   // The method should return a String that contains all
   // the names of the friends of this Person object.
   public String getFriends()
   {
        //write your code here.
   }

    public void addFriend(Person friend)
    {
        friends = friends + " " + friend.name;
    }

    public void unfriend(Person nonFriend)
    {
        friends = friends.replace(" " + nonFriend.name, "");
    }
}

public class PersonDemo 
{
    public static void main(String[] args)
    {
        Person maria = new Person("Maria");
        Person jamesha = new Person("Jamesha");
        Person tj = new Person("TJ");
        
        maria.addFriend(jamesha);
        maria.addFriend(tj);
        maria.unfriend(jamesha);

        System.out.println(maria.getFriends());
    }
}