package thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {
    private static class People{
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public People(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    private static final AtomicReference<People> ATOMIC_REFERENCE = new AtomicReference<>();

    public static void main(String[] args) {
        People people = new People("haha",12);
        ATOMIC_REFERENCE.set(people);
        // ABA 问题
        ATOMIC_REFERENCE.compareAndSet(people,null);
        ATOMIC_REFERENCE.compareAndSet(null,people);
        System.out.println(ATOMIC_REFERENCE.get().age);
        People newPeople = new People("hehe",15);
        ATOMIC_REFERENCE.compareAndSet(people,newPeople);
        System.out.println(ATOMIC_REFERENCE.get().age);

    }
}
