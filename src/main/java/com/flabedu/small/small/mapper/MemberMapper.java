package com.flabedu.small.small.mapper;

import com.flabedu.small.small.dao.MemberDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {
    List<MemberDao> list();

    MemberDao findMemberById(String userId);
}
