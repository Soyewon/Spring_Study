package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter // Getter, setter를 자동으로 만들어줌(Lombok이)
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfs");

        // String name = helloLombok.getName();
        // System.out.println("name = " + name);
        System.out.println("helloLombok = " + helloLombok);
    }
}
