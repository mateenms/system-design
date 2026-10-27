package solid.lsp.good;

/**
 * Eagle CAN fly, so it implements FlyingBird.
 */
public class Eagle implements FlyingBird {

    @Override
    public String getName() { return "Eagle"; }

    @Override
    public void eat() { System.out.println("Eagle is hunting and eating."); }

    @Override
    public void makeSound() { System.out.println("Eagle: Screech!"); }

    @Override
    public void fly() { System.out.println("Eagle is soaring at high altitude!"); }
}
