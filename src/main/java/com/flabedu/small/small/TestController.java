package com.flabedu.small.small;

import com.flabedu.small.small.repository.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    MemberMapper memberMapper;

    @GetMapping("/test")
    public void test(){
        memberMapper.list().forEach(v -> System.out.println(v.getUserId()));
    }
}
