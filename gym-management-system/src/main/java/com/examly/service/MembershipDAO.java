package com.examly.service;

import com.examly.entity.Membership;
import java.util.List;

public interface MembershipDAO {
    boolean addMembership(Membership m);
    List<Membership> getMembershipsByMember(int memberId);
}