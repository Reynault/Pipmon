package model.dungeon.entity;

import model.dungeon.entity.behavior.Behavior;
import model.global.GlobalDirection;
import model.global.GlobalSprites;
import model.global.Position;
import sprite.spriteManager.SpriteManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected int maxHp;
    protected int hp;
    protected boolean passThrought;
    protected Position position;
    protected Behavior behavior;
    protected SpriteManager spriteManager;

    protected Entity(int hp, boolean passThrought, Position position, Behavior behavior, SpriteManager spriteManager) {
        this.maxHp = hp;
        this.hp = hp;
        this.passThrought = passThrought;
        this.position = position;
        this.behavior = behavior;
        this.spriteManager = spriteManager;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public Position getPosition(){
        return position;
    }

    public void setPosition(Position position){
        this.position = position;
        spriteManager.setSprite(position.getGlobalDirection());
    }

    public boolean canPassThrought(){
        return passThrought;
    }

    public void setPassThrought(boolean value){
        passThrought = value;
    }

    public GlobalDirection behave(GlobalDirection direction){
        return behavior.behave(this, direction);
    }

    /**
     * Draw the entity at a given position
     * @param img the image in which the entity will be drawn
     * @param x x position
     * @param y y position
     * @param scale scale of the entity
     */
    public void draw(BufferedImage img, int x, int y, int scale){
        Graphics2D crayon = (Graphics2D) img.getGraphics();
        crayon.drawImage(spriteManager.getCurrentSprite(),
                x,
                y,
                spriteManager.getCurrentSprite().getWidth() * scale,
                spriteManager.getCurrentSprite().getHeight() * scale,
                null);
    }
}
