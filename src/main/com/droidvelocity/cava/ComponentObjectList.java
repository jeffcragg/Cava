package com.droidvelocity.cava;

import java.util.LinkedList;

/*
 *Convenience class for those who believe either
 * LinkedList<Object> x = new LinkedList();
 * is faster than
 * List<Object> x = new LinkedList();
 * or want to bench different lists to see which is best.
 */
public class ComponentObjectList extends LinkedList<ComponentObject> {

    long generationCompleteTimestamp;

    public ComponentObjectList() {
        super();
        generationCompleteTimestamp = System.currentTimeMillis();
    }

}
