package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {
    List<MemberDAO> list();

    MemberDAO findMemberById(String userId);
}
