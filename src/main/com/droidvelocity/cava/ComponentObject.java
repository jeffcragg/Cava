package com.droidvelocity.cava;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 *
 */
public class ComponentObject {

    public String componentObjectTag;

    @NotNull
    public Map<Class<? extends Component>, Component> componentMap = new HashMap<Class<? extends Component>, Component>();

    /*
     * Get components.
     */
    @NotNull
    public <T extends Component> T getComponent(Class<T> componentClass) {
        return (T) componentMap.get(componentClass);
    }

    /*
     * Put components
     */
    public <T extends Component> void putComponent(Class<T> componentClass, @NotNull T component) {
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

