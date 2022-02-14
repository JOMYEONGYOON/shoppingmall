package com.portfolio.shoppingmall.test;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(of = {"id", "name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestTeam {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    List<TestMember> members = new ArrayList<>();

    public TestTeam(String name) {
        this.name = name;
    }

}
