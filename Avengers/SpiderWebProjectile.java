import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SpiderWebProjectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpiderWebProjectile extends HeroProjectile
{
    public SpiderWebProjectile() 
    {
        GreenfootImage up = new GreenfootImage("./images/web.png");
        this.speed = 7;
        this.damage = 15;
        up.scale(100,40);
        setImage(up);
    }    
}