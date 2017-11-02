# Cava
Lightweight entity component system for Java

The goal of Cava is to provide a lightweight free to use entity component system which is extremely easy to use and
open to customisation. Typically this decoupling is used for games although its also a J2EE pattern to obtain services.
There is a heavy reliance on generics for type safety.

For the main component object you can put,get,remove components. Components are organised by their class. So by
subclassing component for Lazer and FireControl;


```java
Lazer lazer= new Lazer();
FireControl fireControl = new FireControl();
cObject.add(Lazer.class, new Lazer());
cObject.add(FireControl.class, fireControl);
```

Then within the FireControl class;

```java
parent.getComponent(Lazer.class).fire();
```

Typically for games you would run through the workflow incrementally to follow dependencies i.e.

1) Do MovementController
2) Do CollisionDetection
3) Do UpdateForces/Velocity
4) Do FireControl
5) Do GenerateDrawList
6) Do GrabUserControlState

From this the flow does not follow the objects themselves, but through collections of similar components. The component
class maintains a list of these registered components. So in your game(its probably a game...) loop;

```java
List<FireControl> fireControlList = Component.getComponentList(FireControl.class);
for (FireControl fc : fireControlList) {
fc.randomFire();
}
```


Note that when you remove a componentObject, because there are lists maintained in Component, to avoid having to generate
unique Component lists per iteration, then you -must- clear this list.

```
Component.removeComponentObject( deadComponentObject );
```

Enjoy
!


