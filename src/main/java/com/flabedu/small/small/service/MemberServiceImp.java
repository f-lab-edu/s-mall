package com.flabedu.small.small.service;

import com.flabedu.small.small.repository.MemberMapper;
import com.flabedu.small.small.service.contract.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImp implements MemberService {
    private MemberMapper memberMapper;

}
