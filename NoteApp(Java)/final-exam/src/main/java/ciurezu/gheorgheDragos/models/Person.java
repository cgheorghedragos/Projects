package ciurezu.gheorgheDragos.models;


public class Person {
    private String realName;
    private int age;

    public Person(String realName, int age) {
        this.realName = realName;
        this.age = age;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
