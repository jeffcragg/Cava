package com.droidvelocity.cava;

import java.util.*;

/**
 *
 */
public class ComponentObject {

    public String componentObjectTag;

    public Map<Class<? extends Component>, Component> componentMap = new HashMap<Class<? extends Component>, Component>();

    /*
     * Get components.
     */
    public <T extends Component> T getComponent(Class<T> componentClass) {
        return (T) componentMap.get(componentClass);
    }

    /*
     * Put components
     */
    public <T extends Component> void putComponent(Class<T> componentClass, T component) {
        component.parentObject = this;
        componentMap.put(componentClass, component);
        Component.addRegisteredComponent(component);
    }

    /*
     * Remove component.
     */
    public <T extends Component> void removeComponent(Class<T> componentClass) {
        final T component = getComponent(componentClass);
        componentMap.remove(componentClass);
        Component.removeRegisteredComponent(component);
    }

}

