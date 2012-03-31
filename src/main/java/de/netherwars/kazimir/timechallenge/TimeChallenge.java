/*
 * Copyright (C) 2012 kazimir <kazimir@netherwars.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.netherwars.kazimir.timechallenge;

import de.netherwars.kazimir.timechallenge.listener.ChallengeSignListener;
import de.netherwars.kazimir.timechallenge.objects.Challenge;
import de.netherwars.kazimir.timechallenge.objects.Checkpoint;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeChallenge extends JavaPlugin {
    private static final Logger LOG = Logger.getLogger("Minecraft.TimeChallenge");
    private ChallengeManager challengeManager;

    @Override
    public void onEnable() {
        startup();
        this.challengeManager = new ChallengeManager(this);
        new ChallengeSignListener(this);

        LOG.info(this + " is now enabled");
    }

    @Override
    public void onDisable() {
        shutdown();
        this.challengeManager = null;
        LOG.info(this + " is now disabled");
    }

    public ChallengeManager getChallengeManager() {
        return this.challengeManager;
    }

    private void startup() {
        ConfigurationSerialization.registerClass(Challenge.class, "Timechallenge.Challenge");
        ConfigurationSerialization.registerClass(Checkpoint.class, "Timechallenge.Checkpoint");
        Configuration config = getConfig();


        if (config.getKeys(true).isEmpty()) {
            config.options().copyDefaults(true);
        }

    }

    public void saveChallenge(Challenge challenge) {
        File challengeFile = new File(getDataFolder() + "/challenges", challenge.getName() + ".yml");
        YamlConfiguration challengeConfig = YamlConfiguration.loadConfiguration(challengeFile);
        challengeConfig.set("challenge", challenge);
        try {
            challengeConfig.save(challengeFile);
        } catch (IOException ex) {
            LOG.log(Level.WARNING, this + " Could not save challenge to " + challengeFile, ex);
        }
    }

    public Challenge loadChallenge(String name) {
        File challengeFile = new File(getDataFolder() + "/challenges", name + ".yml");
        YamlConfiguration challengeConfig = YamlConfiguration.loadConfiguration(challengeFile);
        Challenge c = (Challenge) challengeConfig.get("challenge");
        return c;
    }


    private void shutdown() {
        saveConfig();
    }

    public static Logger getLog() {
        return LOG;
    }
}
