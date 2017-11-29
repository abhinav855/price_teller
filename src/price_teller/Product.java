
package price_teller;

public class Product {
    private final int id;
    private final String name;
    private final float price;
    private final String adddate;
    private final byte[] picture;
    public Product(int pid, String pname, float pprice, String pAddDate,byte [] pimg)
    {
        this.id=pid;
        this.name=pname;
        this.price=pprice;
        this.adddate=pAddDate;
        this.picture=pimg;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public float getPrice()
    {
        return price;
    }
    public String getDate()
    {
        return adddate;
    }
    public byte[] getImage()
    {
        return picture;
    }
}
