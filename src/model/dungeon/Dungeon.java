package model.dungeon;

import engine.Game;
import model.ZelpopGame;
import model.dungeon.entity.EntityFactory;
import model.dungeon.entity.Hero;
import model.dungeon.mazeFactory.MazeFactory;
import model.global.Cmd;
import model.global.Position;
import model.state.StateFactory;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.Serializable;

public class Dungeon implements Serializable {

    private Maze currentMaze;
    private MazeFactory mazeFactory;
    private Hero hero;

    private static int DEFAULT_MAZE_SIZE = 20;

    /**
     * Default constructor with random generated maze
     */
    public Dungeon() {
        this.mazeFactory = new MazeFactory();
        this.hero = EntityFactory.getInstance().generateHero();
        currentMaze = mazeFactory.getRandomMaze(DEFAULT_MAZE_SIZE);
    }

    /**
     * Constructor with filepath containing the maze
     * @param filepath
     */
    public Dungeon(String filepath) {
        this.mazeFactory = new MazeFactory();
        this.hero = EntityFactory.getInstance().generateHero();
        InputStream file = Dungeon.class.getClassLoader().getResourceAsStream(filepath);
        generateMaze(file);
    }


    /**
     * Move the hero with the direction
     * @param direction direction for the move
     */
    public void moveHero(Cmd direction) {
        currentMaze.moveEntity(
                hero,
                direction
        );
    }


    /**
     * Entity is attacking
     */
    public void attack(){
        currentMaze.attack();
    }

    /**
     * Generate a maze
     */
    private void generateMaze() {
        this.currentMaze = mazeFactory.getMaze();
    }

    /**
     * Generate a maze with a file
     * @param filename name of the file
     */
    private void generateMaze(InputStream filename) {
        this.currentMaze = mazeFactory.getMaze(filename);
    }

    /**
     * Update the whole dungeon
     */
    public void updateAll(ZelpopGame game) {
        currentMaze.moveEntities();
        if(!hero.isAlive()){
            game.setState(StateFactory.getGameOver(this));
        }
    }

    /**
     * Give an image for each tile and entity and the hero (used after a load)
     */
    public void setImages() {
        this.currentMaze.setImages();
        this.hero.setImage();
    }

    /**
     * Drawn the dungeon
     * @param img image
     */
    public void draw(BufferedImage img) {
        currentMaze.draw(img);
    }

    public void changeLevel(){
        if(currentMaze.isFinished()){
            currentMaze = mazeFactory.getRandomMaze(DEFAULT_MAZE_SIZE);
            hero.setPosition(new Position(
                    EntityFactory.HERO_X,
                    EntityFactory.HERO_Y,
                    EntityFactory.HERO_DIRECTION));
        }
    }
}
