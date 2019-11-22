import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Thanos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Thanos extends Actor
{
    protected int health = 30;
    protected int damage = 10;
    protected Counter counter;
    protected Player player;
    protected int hitImageCounter = 0;
    protected int defaultImageCounter = 1;
    protected int imgNum = 0;
    int randomX, randomY;
    int timer = 0, spaceStoneTimer = 0;
    int shootTime = 500;
    Boolean flag = true;
    GreenfootImage wormhole = new GreenfootImage("./images/enemy/thanos/wormhole.jpg");
    
    public Thanos(Player mainPlayer, Counter counter) {
        this.counter = counter;
        this.player = mainPlayer;
         wormhole.scale(170,120);
        
    }
    
    public void act(){
        
        timer++;
        spaceStoneTimer++;
        changeLocation();
        shootTime--;
        if (shootTime == 0) {
           fireProjectile();
           shootTime = 500;
       }
        moveAround();
        hitByProjectile();
        
        
    }  
    
    public void moveAround() {
        if (this.hitImageCounter > 0)
            this.hitImageCounter--;
        else 
            {
                 if(spaceStoneTimer >= 400 && spaceStoneTimer <= 500)
                    {
                        setImage("/enemy/thanos/t.png");
                        getWorld().getBackground().drawImage(wormhole, getX() - 80, getY() + 100);
                    }
                    
                else
                setDefaultImage();
            }
        
        setRotation(0);
    } 
    
    public void setScaling() {
        GreenfootImage image = getImage();
        image.scale(150, 158);
    }
    
    public void fireProjectile() {        
        getWorld().addObject(new ThanosProjectile(player, this.getX(),getX() < player.getX()), getX(), getY());
    }
    
    public void hitByProjectile() {
        
        HeroProjectile projectile = (HeroProjectile) getOneIntersectingObject(HeroProjectile.class);
        Actor superprojectile = getOneIntersectingObject(SuperProjectile.class);
        
        if(projectile != null)
        {
            health -= projectile.getDamage();
            setHitImage();
            getWorld().removeObject(projectile);
        }
        if(superprojectile != null)
        {
            counter.score++;
            getWorld().removeObject(superprojectile);
            getWorld().removeObject(this);
        }
        
        if (health <= 0) {
            counter.score++;
           getWorld().removeObject(this);
        }
        
    }
    
    public void setHitImage() {
        this.hitImageCounter = 50;
        setImage("/enemy/" + this.getClass().getName().toLowerCase() + "/" + "hit.png");
        getImage().mirrorHorizontally();
        setScaling(); 
    }
    
    public void setDefaultImage() {  
        defaultImageCounter--;
        if (defaultImageCounter == 0) {
            defaultImageCounter = 20;
            imgNum =(imgNum + 1) % 2;
            
            setImage("/enemy/" + this.getClass().getName().toLowerCase() + "/" + imgNum + ".png");
             if (getX() < player.getX())
                getImage().mirrorHorizontally();
        }
        setScaling();
    }
    
    public void changeLocation()
    {
        
        if(timer % 500 == 0)
        {
            randomX = Greenfoot.getRandomNumber(1100);
            randomY = Greenfoot.getRandomNumber(700);
            if(randomX <= player.getX() - 50 || randomX == player.getX() + 50)
            {
                randomX += 300;
            }
            
            this.setLocation(randomX, randomY);
            spaceStoneTimer = 0;
            getWorld().setBackground("./images/ThorSpaceImage.png");
            
        }
    }
    
    
}
