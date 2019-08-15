package io.quind.training.basicconsoleapp.adapters.userinterface;

import io.quind.training.basicconsoleapp.domain.Calculator;
import io.quind.training.basicconsoleapp.domain.Member;
import io.quind.training.basicconsoleapp.domain.MultiClubMember;
import io.quind.training.basicconsoleapp.domain.SingleClubMember;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    private Scanner reader;

    public MembershipManagement() {
        reader = new Scanner(System.in);
    }

    public MembershipManagement(Scanner reader) {
        this.reader = reader;
    }

    private int getIntInput(int size) {
        int choice = 0;
        size = size == -2 ? Integer.MAX_VALUE : size;
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                if (choice == 0 || choice > size)
                    throw new InputMismatchException();
                reader.nextLine();
            } catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("ERROR: INVALID INPUT. Please try again: ");
            }
        }
        return choice;
    }

    private void printClubOptions() {
        System.out.println("1) Club Mercury\n" +
                "2) Club Neptune\n" +
                "3) Club Jupiter\n" +
                "4) Multi Clubs\n");
    }

    public int getChoice() {
        int choice;
        System.out.println("WELCOME TO CI/CD FITNESS CENTER\n" +
                "=================================\n" +
                "1) Add Member\n" +
                "2) Remove Member\n" +
                "3) Display Member Information\n" +
                "\nPlease select an option (or Enter -1 to quit): ");
        choice = getIntInput(3);
        return choice;
    }

    public String addMembers(LinkedList<Member> memberList) {
        String name;
        int club;
        String memberString;
        double fees;
        int memberID;
        Member member;
        Calculator<Integer> cal;

        System.out.print("Enter member name: ");
        name = reader.nextLine();

        printClubOptions();
        System.out.print("Please select one of the options before: ");
        club = getIntInput(4);

        if (!memberList.isEmpty()) {
            memberID = memberList.getLast().getMemberID() + 1;
        } else {
            memberID = 1;
        }

        if (club != 4) {
            cal = n -> {
                switch (n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            member = new SingleClubMember(memberID, 'S', name, fees, club);
            memberList.add(member);
            memberString = member.toString();
            System.out.println("\nSTATUS: Single Club Member added\n");
        } else {
            cal = n -> n == 4 ? 1200 : -1;
            fees = cal.calculateFees(club);
            member = new MultiClubMember(memberID, 'M', name, fees, 100);
            memberList.add(member);
            memberString = member.toString();
            System.out.println("\nSTATUS: Multi Club Member added\n");
        }
        return memberString;
    }

    public void removeMember(LinkedList<Member> memberList) {
        int memberID;
        System.out.println("Please enter the memberID of the member you want to remove: ");
        memberID = getIntInput(-2);
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberID() == memberID) {
                memberList.remove(i);
                System.out.println("\nSTATUS: The member has been removed\n");
                return;
            }
        }
        System.out.println("\nSTATUS: The member does not exist\n");
    }

    public void printMemberInfo(LinkedList<Member> memberList) {
        int memberID;
        System.out.println("Please enter the memberID of the member you want to view: ");
        memberID = getIntInput(-2);
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberID() == memberID && memberID == memberList.get(i).getMemberID()) {
                String[] memberInfo = memberList.get(i).toString().split(",");

                System.out.println("\nMember Type = " + memberInfo[0] +
                        "\nMember ID = " + memberInfo[1] +
                        "\nMember Name = " + memberInfo[2] +
                        "\nMembership Fees = " + memberInfo[3] +
                        (memberInfo[0].equals("S") ? "\nClub ID = " : "\nMembership Points = ") + memberInfo[4]);
                return;
            }
        }
        System.out.println("\nSTATUS: The member does not exist\n");
    }
}
