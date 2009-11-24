package crimsonportal.googlecode.com.ObjectModel;

public abstract class Weapon
{
    public Weapon() {
        this.clipSize = getDefaultClipSize();
        this.attackSpeed = getDefaultAttackSpeed();
    }
    
    public Weapon(int clipSize, double attackSpeed)
    {
        this.clipSize = clipSize;
        this.attackSpeed = attackSpeed;
    }
    
    public final int getClipSize()
    {
        return clipSize;
    }
    
    public abstract int getDefaultClipSize();
    public abstract double getDefaultAttackSpeed();
    
    protected final void setClipSize(int clipSize)
    {
        this.clipSize = clipSize;
    }
    
    public abstract Bullet spawnBullet(UnitWithWeapon shooter, GameObject target,
            GameTime spawnTime);
    
    @Override
    public abstract Weapon clone();
    
    public int getAttackDamage() {
        return attackDamage;
    }
    
    public double getAttackSpeed() {
        return attackSpeed;
    }
    
    protected int attackDamage;
    protected double attackSpeed;
    private int clipSize;
}
