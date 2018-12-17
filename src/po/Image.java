package po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Image
{
    private String imageId;
    private String originId;
    private String storeLocation;

    @Id
    @Column(name = "imageID")
    public String getImageId()
    {
        return imageId;
    }

    public void setImageId(String imageId)
    {
        this.imageId = imageId;
    }

    @Basic
    @Column(name = "originID")
    public String getOriginId()
    {
        return originId;
    }

    public void setOriginId(String originId)
    {
        this.originId = originId;
    }

    @Basic
    @Column(name = "storeLocation")
    public String getStoreLocation()
    {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation)
    {
        this.storeLocation = storeLocation;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imageId, image.imageId) &&
                Objects.equals(originId, image.originId) &&
                Objects.equals(storeLocation, image.storeLocation);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(imageId, originId, storeLocation);
    }
}
