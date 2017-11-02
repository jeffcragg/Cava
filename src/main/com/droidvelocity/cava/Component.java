package com.droidvelocity.cava;

import java.util.*;
import java.util.Map.Entry;

/**
 * Component instances may be reused or immutable. DisplayComponents must be
 * immutable in order that the componentObjectList may provide a consistent view.
 *
 * @param <T>
 */
public class Component {

    public ComponentObject parentObject;

    public static Map<Class<? extends Component>, List<? extends Component>> registeredComponentsMap =
            new HashMap<Class<? extends Component>, List<? extends Component>>();


    @SuppressWarnings("unchecked")
    public static <T extends Component> void addRegisteredComponent(T component) {
        List<T> list = (List<T>) getComponentList(component.getClass());
        // create a list for this type of component if empty
        if (list == null) {
            list = (List<T>) addRegisteredComponentList(component.getClass());
        }
        list.add(component);
    }

    public static <T extends Component> void addRegisteredComponent(Class<T> componentClass, T component) {
        List<T> list = getComponentList(componentClass);
        // TODO move array list initialisations to the start
        if (list == null) {
            list = addRegisteredComponentList(componentClass);
        }
        list.add(component);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Component> List<T> getComponentList(Class<T> componentClass) {
        List<T> list = (List<T>) registeredComponentsMap.get(componentClass);
        // TODO move array list initialisations to the start
        if (list == null) {
            list = addRegisteredComponentList(componentClass);
        }
        return list;
    }

    public static <T extends Component> List<T> addRegisteredComponentList(Class<T> componentClass) {
        List<T> list = new LinkedList<T>();
        registeredComponentsMap.put(componentClass, list);
        return list;
    }

    public static <T extends Component> void removeRegisteredComponent(T component) {
        @SuppressWarnings("unchecked")
        List<T> list = (List<T>) getComponentList(component.getClass());
        if (list != null) {
            list.remove(component);
        }
    }


    /**
     * Deletes entire list.
     */
    public static void clearAllRegisteredComponents() {
        registeredComponentsMap.clear();
    }

    @SuppressWarnings("unchecked")
    public static void registerAllComponents(Map<Class<? extends Component>, Component> bufferedComponentMap) {
        Set<Entry<Class<? extends Component>, Component>> entrySet = bufferedComponentMap.entrySet();
        for (Entry<Class<? extends Component>, Component> entry : entrySet) {
            addRegisteredComponent((Class<Component>) entry.getKey(), entry.getValue());
        }
    }

    /**
     * Must be called to clear lookup in Component, otherwise memory leak. You have been warned!
     *
     * @param componentObject
     */
    public static void removeComponentObject(final ComponentObject componentObject) {

        final Set<Entry<Class<? extends Component>, Component>> parent = componentObject.componentMap
                .entrySet();
        for (Entry<Class<? extends Component>, Component> entry : parent) {
            Component component = entry.getValue();
            Component.removeRegisteredComponent(component);
        }
    }

}
