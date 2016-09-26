package com.chilvers.henry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Borrowed from...
 * http://stackoverflow.com/questions/12548312/find-all-subsets-of-length-k-in-an-array
 *
 * Created by Henry on 9/25/2016.
 */
class Subsets {
    private Subsets() {
    }

    static <T> List<Set<T>> getSubsets(List<T> superSet, int k) {
        List<Set<T>> res = new ArrayList<>();

        getSubsets(superSet, k, 0, new HashSet<>(), res);

        return res;
    }

    private static <T> void getSubsets(List<T> superSet, int k, int idx, Set<T> current, List<Set<T>> solution) {
        //successful stop clause
        if (current.size() == k) {
            solution.add(new HashSet<>(current));

            return;
        }

        //unseccessful stop clause
        if (idx == superSet.size()) {
            return;
        }

        T x = superSet.get(idx);
        current.add(x);

        //"guess" x is in the subset
        getSubsets(superSet, k, idx + 1, current, solution);

        current.remove(x);

        //"guess" x is not in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
    }
}
