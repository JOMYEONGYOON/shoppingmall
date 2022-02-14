package com.portfolio.shoppingmall.test;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username", "age"})
public class TestMember {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TestTeam team;

    public TestMember(String username, int age, TestTeam team) {
        this.username = username;
        this.age = age;
        if(team != null) {
            changeTeam(team);
        }

    }

    public TestMember(String username) {
        this(username, 0);
    }

    public TestMember(String username, int age) {
        this(username, age, null);

    }

    public void changeTeam(TestTeam team) {
        this.team = team;
        team.getMembers().add(this);

    }
}
