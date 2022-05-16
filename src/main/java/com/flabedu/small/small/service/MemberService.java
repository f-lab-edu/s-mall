package com.flabedu.small.small.service;

import com.flabedu.small.small.repository.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberMapper memberMapper;
    public MemberService(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

}
