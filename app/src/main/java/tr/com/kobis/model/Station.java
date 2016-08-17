package tr.com.kobis.model;

public class Station
{
    String name;
    String emptyPark;
    String bicycleCount;
    StationImage stationImage;

    public Station(){

    }
    // constructor
    public Station(String name, String emptyPark, String bicycleCount){
        this.name = name;
        this.emptyPark = emptyPark;
        this.bicycleCount = bicycleCount;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmptyPark()
    {
        return emptyPark;
    }

    public void setEmptyPark(String emptyPark)
    {
        this.emptyPark = emptyPark;
    }

    public String getBicycleCount()
    {
        return bicycleCount;
    }

    public void setBicycleCount(String bicycleCount)
    {
        this.bicycleCount = bicycleCount;
    }

    public StationImage getStationImage()
    {
        return stationImage;
    }

    public void setStationImage(StationImage stationImage)
    {
        this.stationImage = stationImage;
    }
}