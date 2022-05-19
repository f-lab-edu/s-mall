package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {
    List<Member> list();

    Member getMember(String userId);
}
