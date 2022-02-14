package com.portfolio.shoppingmall.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TestMemberTest {

    @Autowired
    EntityManager em;

    @Test
    public void testEntity() throws Exception {
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


        List<TestMember> select_m_from_testMember_m = em.createQuery("select m from TestMember m", TestMember.class).getResultList();
        List<TestMember> memberList = select_m_from_testMember_m;

        for (TestMember member : memberList) {
            System.out.println("member = " + member);
        }
            //then

    }

}