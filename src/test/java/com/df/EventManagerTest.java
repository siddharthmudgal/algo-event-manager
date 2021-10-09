package com.df;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {

    private int max_capacity;
    private LinkedHashMap<String, Integer> group_wise_capacity;
    private LinkedHashMap<String, HashSet<String>> group_people;
    private EventManager eventManager;

    @BeforeEach
    void beforeEach() {
        max_capacity = -1;
        group_wise_capacity = new LinkedHashMap<>();
        group_people = new LinkedHashMap<>();
        eventManager = new EventManager();
    }

    @Test
    void host_case_infinite_max_capacity_all_groups_allowed() throws Exception {

        max_capacity = Integer.MAX_VALUE;

        group_wise_capacity.put(GROUP_ONE, 5);
        group_wise_capacity.put(GROUP_TWO, 10);
        group_wise_capacity.put(GROUP_THREE, 25);

        HashSet<String> group_one_people = new HashSet<>();
        group_one_people.add(PERSON_ONE);
        group_one_people.add(PERSON_TWO);
        group_one_people.add(PERSON_THREE);

        group_people.put(GROUP_ONE, group_one_people);

        HashSet<String> group_two_people = new HashSet<>();
        group_two_people.add(PERSON_THREE);
        group_two_people.add(PERSON_FOUR);
        group_two_people.add(PERSON_FIVE);
        group_two_people.add(PERSON_SIX);

        group_people.put(GROUP_TWO, group_two_people);

        HashSet<String> group_three_people = new HashSet<>();
        group_three_people.add(PERSON_THREE);
        group_three_people.add(PERSON_FOUR);
        group_three_people.add(PERSON_FIVE);
        group_three_people.add(PERSON_SIX);
        group_three_people.add(PERSON_SEVEN);
        group_three_people.add(PERSON_EIGHT);
        group_three_people.add(PERSON_NINE);
        group_three_people.add(PERSON_TEN);

        group_people.put(GROUP_THREE, group_three_people);


        HashMap<String, Integer> inviteList = eventManager.host(
                max_capacity,
                group_wise_capacity,
                group_people
        );

        assertAll(
                () -> assertEquals( inviteList.get(GROUP_ONE), 3 ),
                () -> assertEquals( inviteList.get(GROUP_TWO), 3 ),
                () -> assertEquals( inviteList.get(GROUP_THREE), 4 )
        );

    }

    @Test
    void host_case_no_common_members_in_all_groups() throws Exception {

        max_capacity = Integer.MAX_VALUE;

        group_wise_capacity.put(GROUP_ONE, 5);
        group_wise_capacity.put(GROUP_TWO, 10);
        group_wise_capacity.put(GROUP_THREE, 25);

        HashSet<String> group_one_people = new HashSet<>();
        group_one_people.add(PERSON_ONE);
        group_one_people.add(PERSON_TWO);
        group_one_people.add(PERSON_THREE);

        group_people.put(GROUP_ONE, group_one_people);

        HashSet<String> group_two_people = new HashSet<>();
        group_two_people.add(PERSON_FOUR);
        group_two_people.add(PERSON_FIVE);
        group_two_people.add(PERSON_SIX);

        group_people.put(GROUP_TWO, group_two_people);

        HashSet<String> group_three_people = new HashSet<>();
        group_three_people.add(PERSON_SEVEN);
        group_three_people.add(PERSON_EIGHT);
        group_three_people.add(PERSON_NINE);
        group_three_people.add(PERSON_TEN);

        group_people.put(GROUP_THREE, group_three_people);


        HashMap<String, Integer> inviteList = eventManager.host(
                max_capacity,
                group_wise_capacity,
                group_people
        );

        assertAll(
                () -> assertEquals( inviteList.get(GROUP_ONE), 3 ),
                () -> assertEquals( inviteList.get(GROUP_TWO), 3 ),
                () -> assertEquals( inviteList.get(GROUP_THREE), 4 )
        );


    }

    @Test
    void host_case_all_members_common_in_all_groups() throws Exception {

        max_capacity = Integer.MAX_VALUE;

        group_wise_capacity.put(GROUP_ONE, 5);
        group_wise_capacity.put(GROUP_TWO, 10);
        group_wise_capacity.put(GROUP_THREE, 25);

        HashSet<String> group_one_people = new HashSet<>();
        group_one_people.add(PERSON_ONE);
        group_one_people.add(PERSON_TWO);
        group_one_people.add(PERSON_THREE);

        group_people.put(GROUP_ONE, group_one_people);
        group_people.put(GROUP_TWO, group_one_people);
        group_people.put(GROUP_THREE, group_one_people);


        HashMap<String, Integer> inviteList = eventManager.host(
                max_capacity,
                group_wise_capacity,
                group_people
        );

        assertAll(
                () -> assertEquals( inviteList.get(GROUP_ONE), 3 ),
                () -> assertEquals( inviteList.get(GROUP_TWO), 0 ),
                () -> assertEquals( inviteList.get(GROUP_THREE), 0 )
        );

    }

    @Test
    public void test_sample_given_with_the_question() throws Exception {

        max_capacity = 7;
        group_wise_capacity.put(GROUP_ONE, 3);
        group_wise_capacity.put(GROUP_TWO, 2);
        group_wise_capacity.put(GROUP_THREE, 2);


        HashSet<String> group_one_people = new HashSet<>();
        group_one_people.add(PERSON_ONE);
        group_one_people.add(PERSON_TWO);
        group_one_people.add(PERSON_THREE);
        group_one_people.add(PERSON_FOUR);

        HashSet<String> group_two_people = new HashSet<>();
        group_two_people.add(PERSON_ONE);
        group_two_people.add(PERSON_TWO);
        group_two_people.add(PERSON_THREE);
        group_two_people.add(PERSON_FOUR);

        group_two_people.add(PERSON_FIVE);
        group_two_people.add(PERSON_SIX);
        group_two_people.add(PERSON_SEVEN);


        HashSet<String> group_three_people = new HashSet<>();
        group_three_people.add(PERSON_FIVE);
        group_three_people.add(PERSON_SIX);
        group_three_people.add(PERSON_SEVEN);
        group_three_people.add(PERSON_EIGHT);


        group_people.put(GROUP_ONE, group_one_people);
        group_people.put(GROUP_TWO, group_two_people);
        group_people.put(GROUP_THREE, group_three_people);

        HashMap<String, Integer> invite_list = eventManager.host(
                max_capacity,
                group_wise_capacity,
                group_people
        );

        assertAll(
                () -> assertEquals(invite_list.get(GROUP_ONE) , 3),
                () -> assertEquals(invite_list.get(GROUP_TWO), 2),
                () -> assertEquals(invite_list.get(GROUP_THREE), 2)
        );

    }

    @Test
    public void test_sample_question_more_groups_than_invited() throws Exception {

        max_capacity = 7;
        group_wise_capacity.put(GROUP_ONE, 3);
        group_wise_capacity.put(GROUP_TWO, 2);
        group_wise_capacity.put(GROUP_THREE, 2);


        HashSet<String> group_one_people = new HashSet<>();
        group_one_people.add(PERSON_ONE);
        group_one_people.add(PERSON_TWO);
        group_one_people.add(PERSON_THREE);
        group_one_people.add(PERSON_FOUR);

        HashSet<String> group_two_people = new HashSet<>();
        group_two_people.add(PERSON_ONE);
        group_two_people.add(PERSON_TWO);
        group_two_people.add(PERSON_THREE);
        group_two_people.add(PERSON_FOUR);

        group_two_people.add(PERSON_FIVE);
        group_two_people.add(PERSON_SIX);
        group_two_people.add(PERSON_SEVEN);


        HashSet<String> group_three_people = new HashSet<>();
        group_three_people.add(PERSON_FIVE);
        group_three_people.add(PERSON_SIX);
        group_three_people.add(PERSON_SEVEN);
        group_three_people.add(PERSON_EIGHT);


        group_people.put(GROUP_ONE, group_one_people);
        group_people.put(GROUP_TWO, group_two_people);
        group_people.put(GROUP_THREE, group_three_people);
        group_people.put(GROUP_FOUR, group_three_people);
        group_people.put(GROUP_FIVE, group_three_people);

        HashMap<String, Integer> invite_list = eventManager.host(
                max_capacity,
                group_wise_capacity,
                group_people
        );

        assertAll(
                () -> assertEquals(invite_list.get(GROUP_ONE) , 3),
                () -> assertEquals(invite_list.get(GROUP_TWO), 2),
                () -> assertEquals(invite_list.get(GROUP_THREE), 2)
        );

    }

    // constants declaration

    private final String GROUP_ONE = "GroupOne";
    private final String GROUP_TWO = "GroupTwo";
    private final String GROUP_THREE = "GroupThree";
    private final String GROUP_FOUR = "GroupFour";
    private final String GROUP_FIVE = "GroupFive";
    private final String GROUP_SIX = "GroupSix";
    private final String GROUP_SEVEN = "GroupSeven";
    private final String GROUP_EIGHT = "GroupEight";
    private final String GROUP_NINE = "GroupNine";
    private final String GROUP_TEN = "GroupTen";


    private final String PERSON_ONE = "PersonOne";
    private final String PERSON_TWO = "PersonTwo";
    private final String PERSON_THREE = "PersonThree";
    private final String PERSON_FOUR = "PersonFour";
    private final String PERSON_FIVE = "PersonFive";
    private final String PERSON_SIX = "PersonSix";
    private final String PERSON_SEVEN = "PersonSeven";
    private final String PERSON_EIGHT = "PersonEight";
    private final String PERSON_NINE = "PersonNine";
    private final String PERSON_TEN = "PersonTen";
}