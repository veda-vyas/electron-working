public class Dog
{
    private Picture pic;
    private Text name;

    public Dog(int x, int y, String file)
    {
        pic = new Picture(file);
        pic.translate(x, y);
        name = new Text(x, y + pic.getHeight(),
                        file.substring(file.indexOf("1//")+3, file.indexOf(".")));
    }

    public void draw()
    {
        pic.draw();
        name.draw();
    }
}
