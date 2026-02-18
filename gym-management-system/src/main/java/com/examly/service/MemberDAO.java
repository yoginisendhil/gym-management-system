package com.examly.service;

import com.examly.entity.Member;
import java.util.List;

public interface MemberDAO {
    boolean addMember(Member member);
    Member getMemberByEmail(String email);
    List<Member> getAllMembers();
}
