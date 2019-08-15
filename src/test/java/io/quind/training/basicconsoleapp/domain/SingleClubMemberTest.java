package io.quind.training.basicconsoleapp.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleClubMemberTest {

    @DisplayName("Should return return correct to string value")
    @ParameterizedTest(name = "{index} => memberID = {0}, memberType = {1}, name = {2}, fees = {3}, club = {4}")
    @CsvSource({
            "1, S, Name1, 100, 1",
            "2, S, Name2, 300, 2",
            "3, S, Name3, 200, 3"
    })
    void testToString(int memberID, char memberType, String name, double fees, int club) {
        SingleClubMember member = new SingleClubMember(memberID, memberType, name, fees, club);
        String expected = String.format("%s, %s, %s, %.1f, %s", memberID, memberType, name, fees, club);
        assertEquals(expected, member.toString());
    }
    @DisplayName("test getter and setter")
    @ParameterizedTest(name = "{index} => memberID = {0}, memberType = {1}, name = {2}, fees = {3}, club = {4}")
    @CsvSource({
            "1, S, Name1, 100, 1",
            "2, S, Name2, 300, 2",
            "3, S, Name3, 200, 3"
    })
    void testGetSet(int memberID, char memberType, String name, double fees, int club) {
        SingleClubMember expectedSingleClubMember = new SingleClubMember(memberID, memberType, name, fees, club);

        SingleClubMember aSingleClubMember = new SingleClubMember(0, 'N', "EMPTY", 0, 0);
        aSingleClubMember.setMemberID(memberID);
        aSingleClubMember.setMemberType(memberType);
        aSingleClubMember.setName(name);
        aSingleClubMember.setFees(fees);
        aSingleClubMember.setClub(club);

        assertEquals(expectedSingleClubMember.getMemberID(), aSingleClubMember.getMemberID());
        assertEquals(expectedSingleClubMember.getMemberType(), aSingleClubMember.getMemberType());
        assertEquals(expectedSingleClubMember.getName(), aSingleClubMember.getName());
        assertEquals(expectedSingleClubMember.getFees(), aSingleClubMember.getFees());
        assertEquals(expectedSingleClubMember.getClub(), aSingleClubMember.getClub());

    }
}