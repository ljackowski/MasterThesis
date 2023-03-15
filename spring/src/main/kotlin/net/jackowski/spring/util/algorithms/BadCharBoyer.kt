package net.jackowski.spring.util.algorithms

class BadCharBoyer {
    companion object {
        var NO_OF_CHARS = 256
    }

    //A utility function to get maximum of two integers
    private fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    //The preprocessing function for Boyer Moore's
    //bad character heuristic
    private fun badCharHeuristic(str: String, size: Int, badChar: IntArray) {

        // Initialize all occurrences as -1
        for (i in 0 until NO_OF_CHARS) badChar[i] = -1

        // Fill the actual value of last occurrence
        // of a character (indices of table are ascii and values are index of occurrence)
        for (i in 0 until size) badChar[str[i].code] = i
    }

    /* A pattern searching function that uses Bad
	Character Heuristic of Boyer Moore Algorithm */
    fun search(txt: String, pat: String) {
        val m = pat.length
        val n = txt.length
        val badChar = IntArray(NO_OF_CHARS)

        /* Fill the bad character array by calling
		the preprocessing function badCharHeuristic()
		for given pattern */badCharHeuristic(pat, m, badChar)
        var s = 0 // s is shift of the pattern with
        // respect to text
        //there are n-m+1 potential alignments
        while (s <= n - m) {
            var j = m - 1

            /* Keep reducing index j of pattern while
			characters of pattern and text are
			matching at this shift s */while (j >= 0 && pat[j] == txt[s + j]) j--

            /* If the pattern is present at current
			shift, then index j will become -1 after
			the above loop */s += if (j < 0) {
                println("Patterns occur at shift = $s")

                /* Shift the pattern so that the next
                        character in text aligns with the last
                        occurrence of it in pattern.
                        The condition s+m < n is necessary for
                        the case when pattern occurs at the end
                        of text */
                //txt[s+m] is character after the pattern in text
                if (s + m < n) m - badChar[txt[s + m].code] else 1
            } else  /* Shift the pattern so that the bad character
                        in text aligns with the last occurrence of
                        it in pattern. The max function is used to
                        make sure that we get a positive shift.
                        We may get a negative shift if the last
                        occurrence of bad character in pattern
                        is on the right side of the current
                        character. */ max(1, j - badChar[txt[s + j].code])
        }
    }
}

/* Driver program to test above function */
fun main() {
    val txt = "ABAAABCD"
    val pat = "ABC"
    val b = BadCharBoyer()
    b.search(txt, pat)
}