package TowerDefense;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.control.AbstractControl;

public class CreepControl extends AbstractControl {
    private GamePlayAppState appState;
    private Node rootNode;

    public CreepControl(GamePlayAppState appState) {
        this.appState = appState;
        this.rootNode = appState.getRootNode();
    }

    @Override
    protected void controlUpdate(float tpf) {
        // Move toward the player
        if(spatial.getLocalTranslation().getZ() > 0){
            // Alive and moving towards the player
            if(getHealth() > 0){
                spatial.move(0, 0, -1 * tpf);
            } else {
                // Died before reaching the player
                appState.setBudget(appState.getBudget() + 1);
                rootNode.detachChild(spatial);
            }
        } else {
            // Reached the player : Subtract health and remove itself
            appState.setHealth(appState.getHealth() - 1);
            rootNode.detachChild(spatial);
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    }
    
    public String getIndex(){
        return (String)spatial.getUserData("index");
    }
    
    public int getHealth(){
        return (Integer)spatial.getUserData("health");
    }
}
