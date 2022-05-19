package com.flabedu.small.small.service;

import com.flabedu.small.small.repository.MemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private MemberMapper memberMapper;

}
