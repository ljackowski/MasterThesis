package net.jackowski.spring.util.algorithms


class FineAutomata {

    companion object {
        private var NO_OF_CHARS = 256
    }

    private fun getNextState(
        pat: String, m: Int,
        state: Int, x: Int
    ): Int {

        // If the character c is same as next
        // character in pattern,then simply
        // increment state
        if (state < m && x == pat[state].code) return state + 1

        // ns stores the result which is next state
        var i: Int

        // ns finally contains the longest prefix
        // which is also suffix in "pat[0..state-1]c"

        // Start from the largest possible value
        // and stop when you find a prefix which
        // is also suffix
        var ns: Int = state
        while (ns > 0) {
            if (pat[ns - 1].code == x) {
                i = 0
                while (i < ns - 1) {
                    if (pat[i] != pat[state - ns + 1 + i]) break
                    i++
                }
                if (i == ns - 1) return ns
            }
            ns--
        }
        return 0
    }

    /* This function builds the TF table which
	represents Finite Automata for a given pattern */
    private fun computeTF(pat: String, m: Int, tf: Array<IntArray>) {
        var x: Int
        var state = 0
        while (state <= m) {
            x = 0
            while (x < NO_OF_CHARS) {
                tf[state][x] = getNextState(pat, m, state, x)
                x++
            }
            state++
        }
    }

    /* Prints all occurrences of pat in txt */
    fun search(pat: String, txt: String): List<String> {
        val m = pat.length
        val n = txt.length
        val tf = Array(m + 1) { IntArray(NO_OF_CHARS) }
        computeTF(pat, m, tf)

        // Process txt over FA.
        var state = 0
        var i = 0
        val resultList: MutableList<String> = ArrayList()
        while (i < n) {
            state = tf[state][txt[i].code]
            if (state == m) resultList.add("Pattern found at index ${i - m + 1}")
            i++
        }
        return resultList
    }
}

// Driver code
/*
fun main() {
    val pat = "AABAACAADAABAAABAA"
    val txt = "AABA"
    val f = FineAutomata()
    f.search(txt, pat)
}*/
