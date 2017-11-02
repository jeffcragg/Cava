package com.droidvelocity.cava;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ComponentTest {

    private ComponentObject cObject;
    private ComponentObject cObjectA;

    class RootComponent extends Component {
        String testValue;
    }

    class SecondaryComponent extends Component {
    }

    @Before
    public void setup() {
        cObject = new ComponentObject();
        cObjectA = new ComponentObject();
        RootComponent.clearAllRegisteredComponents();
    }

    @Test
    public void givenObjectswithComponentsThenAllComponentSeparatelyAccessible() {
        RootComponent component = new RootComponent();
        RootComponent componentA = new RootComponent();

        cObject.putComponent(RootComponent.class, component);
        cObjectA.putComponent(RootComponent.class, componentA);

        List<RootComponent> componentList = Component.getComponentList(RootComponent.class);
        assertTrue(componentList.contains(component));
        assertTrue(componentList.contains(componentA));
    }

    @Test
    public void givenObjectHasComponentRemovedThenComponentIsEntirelyRemoved() {
        cObject.putComponent(RootComponent.class,new RootComponent());
        cObject.removeComponent(RootComponent.class);
        RootComponent component = cObject.getComponent(RootComponent.class);
        assertNull(component);
        assertTrue(Component.getComponentList(RootComponent.class).isEmpty());
    }

    @Test
    public void givenTwoComponentsThenCommonParentComponentsAreAccessible() {
        RootComponent rootComponent = new RootComponent();
        rootComponent.testValue = "rootString";

        cObject.putComponent(RootComponent.class,rootComponent);
        SecondaryComponent secondaryComponent = new SecondaryComponent();
        cObject.putComponent(SecondaryComponent.class,secondaryComponent);
        RootComponent retrievedComponent = secondaryComponent.parentObject.getComponent(RootComponent.class);
        boolean isMatched = retrievedComponent.testValue.equals("rootString");
        assertTrue(isMatched);
    }

    @Test
    public void givenComponentObjectWithRegisteredComponentThenTheyAreRemovedFromComponentCache() {
        cObject.putComponent(RootComponent.class, new RootComponent());
        Component.removeComponentObject(cObject);
        List<RootComponent> componentList = Component.getComponentList(RootComponent.class);
        assertTrue(componentList.isEmpty());
    }

}
