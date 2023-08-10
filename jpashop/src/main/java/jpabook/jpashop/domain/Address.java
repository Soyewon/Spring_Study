package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 내장타
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) { // 생성할 때만 !
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
