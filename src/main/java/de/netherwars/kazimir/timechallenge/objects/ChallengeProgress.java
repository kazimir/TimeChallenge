package de.netherwars.kazimir.timechallenge.objects;

import org.bukkit.entity.Player;

/**
 * User: corwynt
 * Date: 30.03.12
 * Time: 23:58
 */
public class ChallengeProgress {
    private Challenge challenge;
    private Player player;
    private int currentCheckpoint;
    private long startTime = 0;

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCurrentCheckpoint() {
        return currentCheckpoint;
    }

    public void setCurrentCheckpoint(int currentCheckpoint) {
        this.currentCheckpoint = currentCheckpoint;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
