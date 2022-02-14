package com.portfolio.shoppingmall.test;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.portfolio.shoppingmall.test.QTestMember.testMember;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() throws Exception {
        //given
        TestTeam testTeam1 = new TestTeam("TeamA");
        TestTeam testTeam2 = new TestTeam("TeamB");
        em.persist(testTeam1);
        em.persist(testTeam2);

        TestMember member1 = new TestMember("Member1", 10, testTeam1);
        TestMember member2 = new TestMember("Member2", 20, testTeam1);
        TestMember member3 = new TestMember("Member3", 30, testTeam2);
        TestMember member4 = new TestMember("Member4", 40, testTeam2);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();
        //when

    }

    @Test
    public void startJpql() throws Exception {
            //given
        TestMember singleResult = em.createQuery("select m from TestMember m where m.username = :username", TestMember.class)
                .setParameter("username", "Member1")
                .getSingleResult();
        assertThat(singleResult.getUsername()).isEqualTo("Member1");
        //when
            //then

    }

    @Test
    public void startQuerydsl() throws Exception {
            //given
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTestMember m = new QTestMember("m");

        TestMember findMember = queryFactory.select(m).from(m).where(m.username.eq("Member1")).fetchOne();

        //when

        //then
        assertThat(findMember.getUsername()).isEqualTo("Member1");
    }

    @Test
    public void resultfetch() throws Exception {
            //given
        List<TestMember> fetch = queryFactory
                .selectFrom(testMember)
                .fetch();

        TestMember member = queryFactory
                .selectFrom(testMember)
                .fetchOne();

        TestMember member1 = queryFactory
                .selectFrom(testMember)
                .fetchFirst();

        QueryResults<TestMember> testMemberQueryResults = queryFactory
                .selectFrom(testMember)
                .fetchResults();

        //when
            //then

    }

    @Test
    public void paging1() throws Exception {
            //given
        List<TestMember> fetch = queryFactory.selectFrom(testMember)
                .orderBy(testMember.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertThat(fetch.size()).isEqualTo(2);
        //when
            //then

    }

}
