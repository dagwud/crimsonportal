package crimsonportal.googlecode.com.ObjectModel;

public abstract class Weapon
{
    public Weapon() {
        this.clipSize = getDefaultClipSize();
        this.firingRate = getDefaultFiringRate();
    }
    
    public Weapon(int clipSize, float firingRate)
    {
        this.clipSize = clipSize;
        this.firingRate = firingRate;
    }
    
    public final float getFiringRate()
    {
        return firingRate;
    }
    
    public abstract float getDefaultFiringRate();
    
    protected final void setFiringRate(float firingRate)
    {
        this.firingRate = firingRate;
    }
    
    public final int getClipSize()
    {
        return clipSize;
    }
    
    public abstract int getDefaultClipSize();
    
    protected final void setClipSize(int clipSize)
    {
        this.clipSize = clipSize;
    }
    
    public abstract Bullet spawnBullet(UnitWithWeapon shooter, GameObject target);
    
    @Override
    public abstract Weapon clone();
    
    private float firingRate;
    private int clipSize;
}
