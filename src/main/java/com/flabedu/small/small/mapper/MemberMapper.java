package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {
    List<Member> list();

    Member findMemberById(String userId);
}
