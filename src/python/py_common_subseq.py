"""Implementation and variations on calACS-DP.

Implementation of the calACS-DP (all common subsequences - dynamic programming)
algorithm as presented in Hui Wang's "All common subsequences."
IJCAI 2007:635-640 (ACM). This module also introduces a variation which can
produce a list all of those common subsequences. Additionally, both the classic
implementation as well as the variation offer some space optimizations
similar to those found in longest common subsequence imeplementations.

@author: A. Samuel Pottinger (samnsparky, Gleap LLC, http://gleap.org)
@license: MIT
"""

import itertools


def count_common_subsequences(seq_1, seq_2):
    """Find the number of common subsequences between two collections.

    This function finds the number of common subsequences between two
    collections but does not return an actual listing of those subsequences.
    This is more space efficient O(len(seq_1)) than find_common_subsequences.

    > number_of_subsequences = count_common_subsequences('qwer', 'qewr')
    > print number_of_subsequences
    12

    @param seq_1: The first collection to find subsequences in.
    @type seq_1: Any integer indexable collection (list, tuple, etc.)
    @param seq_2: The second collection to find subsequences in.
    @type seq_2: Any integer indexable collection (list, tuple, etc.)
    @return: The number of common subsequences between seq_1 and seq_2.
    @rtype: int
    """
    # Ensure the smaller of the two sequences is used to create the columns for
    # the DP table.
    if len(seq_1) < len(seq_2):
        new_seq_1 = seq_2
        seq_2 = seq_1
        seq_1 = new_seq_1

    # Use length plus one to provide a row and column in the subsequence table,
    # a row / column not corresponding to an element. This provides
    # initialization values to the algorithm and handles the edge case of
    # calculating an element in the subsequence table when that element is
    # either in the first row of the table or is the first element of a row.
    # This also includes / handles the empty string as a substring.
    seq_1_len = len(seq_1)
    seq_2_len = len(seq_2)
    seq_1_len_plus_1 = seq_1_len + 1
    seq_2_len_plus_1 = seq_2_len + 1
    
    # Initialize the first two rows of a "2D array" consisting of
    # seq_1_len_plus_1 by seq_2_len_plus_1 values. Note that, due to space
    # optimizations, only two rows are ever maintained in memory.
    subseq_last_row = [1] * seq_2_len_plus_1
    subseq_current_row = [1] + [0] * seq_2_len

    for row in range(1, seq_1_len_plus_1):

        for col in range(1, seq_2_len_plus_1):

            if seq_1[row-1] == seq_2[col-1]:
                new_cell_value = 2 * subseq_last_row[col - 1]
            else:
                new_cell_value = subseq_last_row[col]
                new_cell_value += subseq_current_row[col-1]
                new_cell_value -= subseq_last_row[col-1]
            subseq_current_row[col] = new_cell_value

        subseq_last_row = subseq_current_row
        subseq_current_row = [1] + [0] * seq_2_len

    return subseq_last_row[seq_2_len]

