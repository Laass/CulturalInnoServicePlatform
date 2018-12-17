package dao;

public enum CollectionType
{
    EXHIBITION("exhibition"),
    NEWS("news"),
    PRODUCT("product"),
    DEMAND("demand"),
    SUPPLY("supply");


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
