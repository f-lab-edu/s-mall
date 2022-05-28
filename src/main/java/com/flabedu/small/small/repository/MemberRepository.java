package com.flabedu.small.small.repository;

import com.flabedu.small.small.model.Member;
import com.flabedu.small.small.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final MemberMapper memberMapper;

    public Member findMemberById(String userId){
        return memberMapper.findMemberById(userId);
    }

    public List<Member> getMembers(){
        return memberMapper.list();
    }
}
