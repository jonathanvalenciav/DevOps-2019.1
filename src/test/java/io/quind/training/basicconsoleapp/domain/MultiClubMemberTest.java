package io.quind.training.basicconsoleapp.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiClubMemberTest {

    @DisplayName("Should return return correct to string value")
    @ParameterizedTest(name = "{index} => memberID = {0}, memberType = {1}, name = {2}, fees = {3}, club = {4}")
    @CsvSource({
            "1, M, Name1, 100, 1000",
            "2, M, Name2, 300, 2000",
            "3, M, Name3, 200, 3000"
    })
    void testToString(int memberID, char memberType, String name, double fees, int membershipPoints) {
        MultiClubMember multiClubMember = new MultiClubMember(memberID, memberType, name, fees, membershipPoints);
        String expected = String.format("%s, %s, %s, %.1f", memberID, memberType, name, fees) + ", " + membershipPoints;
        assertEquals(expected, multiClubMember.toString());
    }

    @DisplayName("test getter and setter")
    @ParameterizedTest(name = "{index} => memberID = {0}, memberType = {1}, name = {2}, fees = {3}, club = {4}")
    @CsvSource({
            "1, M, Name1, 100, 1000",
            "2, M, Name2, 300, 2000",
            "3, M, Name3, 200, 3000"
    })
    void testGetSet(int memberID, char memberType, String name, double fees, int membershipPoints) {
        MultiClubMember expectedMember = new MultiClubMember(memberID, memberType, name, fees, membershipPoints);
        MultiClubMember member = new MultiClubMember(0,'N', "EMPTY", 0, 0);
        member.setMemberID(memberID);
        member.setMemberType(memberType);
        member.setName(name);
        member.setFees(fees);
        member.setMembershipPoints(membershipPoints);
        assertEquals(expectedMember.getMemberID(), member.getMemberID());
        assertEquals(expectedMember.getMemberType(), member.getMemberType());
        assertEquals(expectedMember.getName(), member.getName());
        assertEquals(expectedMember.getFees(), member.getFees());
        assertEquals(expectedMember.getMembershipPoints(), member.getMembershipPoints());
    }
}