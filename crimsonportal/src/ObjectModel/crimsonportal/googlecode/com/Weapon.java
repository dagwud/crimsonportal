package ObjectModel.crimsonportal.googlecode.com;

public class Weapon
{
    public Weapon(int clipSize, float firingRate)
    {
        this.clipSize = clipSize;
        this.firingRate = firingRate;
    }
    
    public float getFiringRate()
    {
        return firingRate;
    }
    
    protected void setFiringRate(float firingRate)
    {
        this.firingRate = firingRate;
    }
    
    public int getClipSize()
    {
        return clipSize;
    }
    
    protected void setClipSize(int clipSize)
    {
        this.clipSize = clipSize;
    }
    
    @Override
    public Weapon clone()
    {
        Weapon w = new Weapon(clipSize, firingRate);
        return w;
    }
    
    private float firingRate;
    private int clipSize;
}
