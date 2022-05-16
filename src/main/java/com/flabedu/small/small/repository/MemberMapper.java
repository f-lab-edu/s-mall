package com.flabedu.small.small.repository;

import com.flabedu.small.small.data.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Select("Select * From member")
    List<Member> list();

    @Select("Select * From member where USERID = #{id}")
    Member getMember(long id);
}
