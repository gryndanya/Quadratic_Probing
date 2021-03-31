import java.util.Objects;
import java.util.Scanner;

class LocationData{
    private String name;
    private int latitude;
    private int longtitude;
    private int temperature;

    LocationData(String name,int latitude,int longtitude,int temperature){
        this.name=name;
        this.latitude=latitude;
        this.longtitude=longtitude;
        this.temperature=temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(int longtitude) {
        this.longtitude = longtitude;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longtitude=" + longtitude +
                ", temperature=" + temperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationData that = (LocationData) o;
        return latitude == that.latitude &&
                longtitude == that.longtitude &&
                temperature == that.temperature &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longtitude, temperature);
    }
}
class QuadraticProbingHashTable
{
    private int currentSize, maxSize;
    private String[] names;
    private LocationData[] vals;

    /** Constructor **/
    public QuadraticProbingHashTable(int capacity)
    {
        currentSize = 0;
        maxSize = capacity;
        names = new String[maxSize];
        vals = new LocationData[maxSize];
    }

    /** Function to clear hash table **/
    public void makeEmpty()
    {
        currentSize = 0;
        names = new String[maxSize];
        vals = new LocationData[maxSize];
    }

    /** Function to get size of hash table **/
    public int getSize()
    {
        return currentSize;
    }

    /** Function to check if hash table is full **/
    public boolean isFull()
    {
        return currentSize == maxSize;
    }

    /** Function to check if hash table is empty **/
    public boolean isEmpty()
    {
        return getSize() == 0;
    }

    /** Fucntion to check if hash table contains a key **/
    public boolean contains(String key)
    {
        return get(key) !=  null;
    }

    /** Functiont to get hash code of a given key **/
    private int hash(String key)
    {
        return key.hashCode() % maxSize;
    }

    /** Function to insert key-value pair **/
    public void insert(String name, LocationData val)
    {
        int tmp = hash(name);
        int i = tmp, h = 1;
        do
        {
            if (names[i] == null)
            {
                names[i] = name;
                vals[i] = val;
                currentSize++;
                return;
            }
            if (names[i].equals(name))
            {
                vals[i] = val;
                return;
            }
            i = (i + h * h++) % maxSize;
        } while (i != tmp);
    }

    /** Function to get value for a given key **/
    public LocationData get(String key)
    {
        int i = hash(key), h = 1;
        while (names[i] != null)
        {
            if (names[i].equals(key))
                return vals[i];
            i = (i + h * h++) % maxSize;
            System.out.println("i "+ i);
        }
        return null;
    }

    /** Function to remove key and its value **/
    public void remove(String key)
    {
        if (!contains(key))
            return;

        /** find position key and delete **/
        int i = hash(key), h = 1;
        while (!key.equals(names[i]))
            i = (i + h * h++) % maxSize;
        names[i] = null;
        vals[i] = null;

        /** rehash all keys **/
        for (i = (i + h * h++) % maxSize; names[i] != null; i = (i + h * h++) % maxSize)
        {
            String tmp1 = names[i];
            LocationData tmp2 = vals[i];
            names[i] = null;
            vals[i] = null;
            currentSize--;
            insert(tmp1, tmp2);
        }
        currentSize--;
    }

    /** Function to print HashTable **/
    public void printHashTable()
    {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < maxSize; i++)
            if (names[i] != null)
                System.out.println(names[i] +" "+ vals[i]);
        System.out.println();
    }
}
