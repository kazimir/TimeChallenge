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
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class TimeChallenge extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft.TimeChallenge");
    private ChallengeManager challengeManager;
    @Override
    public void onEnable() {
        startup();
        this.challengeManager=new ChallengeManager();
        new ChallengeSignListener(this);

        log.info(this + " is now enabled");
    }

    @Override
    public void onDisable() {
        shutdown();
        this.challengeManager=null;
        log.info(this + " is now disabled");
    }
    public ChallengeManager getChallengeManager(){
        return this.challengeManager;
    }
    private void startup() {
        ConfigurationSerialization.registerClass(Challenge.class);
        Configuration config = getConfig();


        if (config.getKeys(true).isEmpty()) {
            config.options().copyDefaults(true);

        }

    }

    private void shutdown() {
        saveConfig();
    }
}
