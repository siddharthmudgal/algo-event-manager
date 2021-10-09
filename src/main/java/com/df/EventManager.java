package com.df;

import java.util.*;

public class EventManager {

    /**
     * This is the entry point function that takes in the input for organizing an event
     * @param max_capacity
     * @param group_wise_capacity
     * @param group_people
     * @return
     */

    public HashMap<String, Integer> host(
            int max_capacity,
            LinkedHashMap<String, Integer> group_wise_capacity,
            LinkedHashMap<String, HashSet<String>> group_people
    ) throws Exception {

        // stores the invitee number per group
        HashMap<String, Integer> invite_list = new HashMap<>();
        // used to check if a person has already been invited
        Set<String> invitees = new HashSet<>();

        // validate input
        if (!checkParams(max_capacity, group_wise_capacity, group_people))
            return invite_list;


            // iterate only till invite capacity left
        while ( max_capacity > 0 ) {

            // parse over individual groups to figure out who all can be invited
            for (Map.Entry<String, HashSet<String>> entry : group_people.entrySet()) {

                // check if this group is part of the invitation list
                if (
                        !group_wise_capacity.containsKey(entry.getKey()) ||
                        group_wise_capacity.get(entry.getKey()) <= 0
                ) {
                    continue;
                }

                // initialize total invitees from this group as zero
                invite_list.put(entry.getKey(), 0);

                HashSet<String> people = entry.getValue();
                Iterator<String> peopleIterator = people.iterator();

                // iterate over the people present in this group
                while (peopleIterator.hasNext()) {

                    String person  = peopleIterator.next();

                    // check if the person has already been invited
                    if (invitees.contains(person))
                        continue;

                    // add this person to invitee list
                    invitees.add(person);

                    // increment total invitees
                    --max_capacity;

                    // update the invite_list
                    Integer temp = invite_list.get(entry.getKey());
                    invite_list.replace(
                                entry.getKey(),
                                ++temp
                            );

                    // decrement group capacity
                    temp = group_wise_capacity.get(entry.getKey());
                    group_wise_capacity.replace(entry.getKey(), --temp);

                    // break if group capacity has been exhausted
                    if (temp <= 0)
                        break;
                }

            }
            // if parsing all groups has been exhausted and the capacity still remains
            break;

        }

        return invite_list;

    }

    /**
     * helper utility to verify inputs
     * @param max_capacity
     * @param group_wise_capacity
     * @param group_people
     * @return
     */
    private boolean checkParams(
            int max_capacity,
            Map<String, Integer> group_wise_capacity,
            Map<String, HashSet<String>> group_people
    ) throws Exception {
        if (max_capacity <= 0)
            throw new Exception("max_capacity invalid");

        if (group_wise_capacity.size() == 0)
            throw new Exception("group_wise_capacity invalid");

        if (group_people.size() == 0)
            throw new Exception("group_people invalid");

        return true;
    }

}
