# Cava
Lightweight entity component system for Java

The goal of Cava is to provide a lightweight free to use entity component system which is extremely easy to use and
open to customisation. Typically this decoupling is used for games although its also a J2EE pattern to obtain services.

For the main component object you can put,get,remove components. Components are organised by their class.


```java
Lazer lazer= new Lazer();
FireControl fireControl = new FireControl();
cObject.add(Lazer.class, new Lazer());
cObject.add(FireControl.class, fireControl);
```





