package model.dungeon.scoring;

import model.dungeon.entity.Entity;
import model.dungeon.tile.Tile;

import java.io.Serializable;

public class Scoring implements Serializable {
    private int score;
    private int multiplier;

    Scoring(int multiplier, int score) {
        this.score = score;
        this.multiplier = multiplier;
    }


    public double killMonster(Entity entity) {
        double aa = entity.getValue() * entity.getMultiplier() * multiplier;
        System.out.println(aa);
        return aa;
    }

    public int leaveMaze() {
        return score * multiplier;
    }

    public int findChest(Tile tile) {
        return tile.getGold() * multiplier;
    }
}
