package com.flabedu.small.small.mapper;

import com.flabedu.small.small.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<Member> list();

    Member findMemberById(String userId);
}
