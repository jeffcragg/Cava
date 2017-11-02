package com.droidvelocity.cava;


import java.util.List;

class Shield extends Component{

    boolean isRaised=false;

    void raise() {
        isRaised=true;
        System.out.println("Shields raised!");
    }

    void lower() {
        isRaised=false;
        System.out.println("Shields lowered");
    }
}

/**
 * This component is loosely coupled with Shield.
 */
class Lazer extends Component {
    void fire() {
        Shield shield = parentObject.getComponent(Shield.class);
        if (shield!=null) {
            shield.lower();
        }
        System.out.println("Fire!");
    }
}

/**
 * Tutorial shows;
 * 1) Component object with two components
 * 2) Iterating through components of different objects
 * 3) Removing components
 */
public class CavaTutorial {

    public static void main(String[] args) {
        new CavaTutorial().run();
    }

    private void run() {
        ComponentObject shipX= new ComponentObject();
        shipX.putComponent( Lazer.class, new Lazer());
        shipX.putComponent( Shield.class, new Shield());

        ComponentObject shipY= new ComponentObject();
        shipY.putComponent( Lazer.class, new Lazer());

        fireAll();

        shipX.removeComponent(Shield.class);

        fireAll();
    }

    private void fireAll() {
        //Fire all lazers
        List<Lazer> lazerList = Lazer.getComponentList(Lazer.class);
        for (Lazer lazer : lazerList) {
            lazer.fire();
        }
    }

}
