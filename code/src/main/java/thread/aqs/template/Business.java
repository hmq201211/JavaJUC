package thread.aqs.template;


public class Business extends Template {
    @Override
    public void greet() {
        System.out.println("hello");
    }

    @Override
    public void contact() {
        System.out.println("do business");
    }

    @Override
    public void goodbye() {
        System.out.println("say goodbye");
    }

    public static void main(String[] args) {
        new Business().meet();
    }
}
