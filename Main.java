package core;

import Addons.Sleep;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "cheshnut", name = "Remove Toad Legs", info = "This bot will remove toad legs for profit.", version = 0.1, logo = "")
public class Main extends Script {

    @Override
    public void onStart() {
        log("Starting Toad legs remover bot...");
    }

    @Override
    public void onExit() {
        log("Thank you for using my script!");
    }

    @Override
    public int onLoop() throws InterruptedException {
        Area geArea = new Area(3158, 3494, 3170, 3485);
        if (geArea.contains((Entity)myPlayer())) {
            if (!this.inventory.contains(new String[] { "Swamp toad" })) {
                ((NPC)getNpcs().closest(new String[] { "Banker" })).interact(new String[] { "Bank" });
                Sleep.sleepUntil(() -> getBank().isOpen(), 8000);
                if (this.inventory.contains(new String[] { "Toad's legs" })) {
                    this.bank.depositAll();
                    if (this.bank.isOpen())
                        getBank().withdraw("Swamp toad", 28);
                        Sleep.sleepUntil(() -> this.inventory.contains(new String[] { "Swamp toad" }), 5000);
                        if (this.bank.isOpen())
                            this.bank.close();
                    }
                    if (this.inventory.contains(new String[] { "Swamp toad" })) {
                    	for (int i = 0; i < 28; i++) {
                        	if (this.inventory.contains(new String[] { "Swamp toad" })) {
                        		getInventory().interact(27, "Remove-legs");
                        		Sleep.sleepUntil(() -> !this.inventory.contains(new String[] { "Swamp toad" }), 100);
                        	}
                    	}
                    }
                }
            }
        return 0;
    }
}
