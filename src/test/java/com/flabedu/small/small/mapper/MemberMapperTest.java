package com.flabedu.small.small.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("Member를 반환한다. 에러 없이 성공한다.")
    public void listTest(){
        memberMapper.list();
    }

    @Test
    @DisplayName("멤버 id로 멤버를 찾는다. 찾은 멤버의 id가 동일하다.")
    public void getMemberTest(){
        String id = "test_user";

        var member = memberMapper.findMemberById(id);

        Assertions.assertEquals(member.getUserId(), id);
    }
}
