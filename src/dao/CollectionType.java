package dao;

public enum CollectionType
{
    EXHIBITION("exhibition"),
    NEWS("news"),
    PRODUCT("product"),
    SUPPLYDEMAND("supplydemand");

    public final String name;

    CollectionType(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return name;
    }
}
