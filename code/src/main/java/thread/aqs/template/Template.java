package thread.aqs.template;

public abstract class Template {
    public abstract void greet();

    public abstract void contact();

    public abstract void goodbye();

    public void meet() {
        greet();
        contact();
        goodbye();
    }
}
