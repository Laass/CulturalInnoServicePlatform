package dao;

public enum ProductType
{
    CALLIGRAPHY("calligraphy"),
    PAINTING("painting"),
    MUSINSTRU("musicInstrument"),
    GARMENT("garment");

    public final String name;

    ProductType(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }
}
