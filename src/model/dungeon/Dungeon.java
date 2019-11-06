package model.dungeon;

import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.mazeFactory.MazeFactory;
import model.global.GlobalDirection;

import java.awt.image.BufferedImage;
import java.io.File;

public class Dungeon {

    private Maze currentMaze;
    private MazeFactory mazeFactory;
    private Hero hero;

    /**
     * Default constructor
     */
    public Dungeon(){
        this.mazeFactory = new MazeFactory();
        this.hero = EntityFactory.getInstance().generateHero();
        File file = new File("ressources/level/firstLevel.txt");
        generateMaze(file);
    }

    public Dungeon(String filepath){
        this.mazeFactory = new MazeFactory();
        this.hero = EntityFactory.getInstance().generateHero();
        File file = new File(filepath);
        generateMaze(file);
    }


    /**
     * Move the hero with the direction
     * @param direction direction for the move
     */
    public void moveHero(GlobalDirection direction){
        currentMaze.moveEntity(
                hero,
                direction
        );
    }

    /**
     * Generate a maze
     */
    private void generateMaze(){
        this.currentMaze = mazeFactory.getMaze();
    }

    /**
     * Generate a maze with a file
     * @param filename name of the file
     */
    private void generateMaze(File filename){
        this.currentMaze = mazeFactory.getMaze(filename);
    }

    /**
     * Update the whole dungeon
     */
    public void updateAll(){
        currentMaze.moveEntities();
    }

    /**
     * Drawn the dungeon
     * @param img
     */
    public void draw(BufferedImage img){
        currentMaze.draw(img);
    }
}
