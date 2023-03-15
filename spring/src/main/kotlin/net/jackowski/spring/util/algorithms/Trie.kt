package net.jackowski.spring.util.algorithms

class Trie {
    // Alphabet size (# of symbols)
    companion object {
        const val ALPHABET_SIZE = 26
    }
    private var root: TrieNode? = TrieNode()

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    fun insert(key: String) {
        val length = key.length
        var index: Int
        var pCrawl = root
        var level: Int = 0
        while (level < length) {
            index = key[level].code - 'a'.code
            if (pCrawl!!.children[index] == null) pCrawl.children[index] = TrieNode()
            pCrawl = pCrawl.children[index]
            level++
        }

        // mark last node as leaf
        pCrawl!!.isEndOfWord = true
    }

    // Returns true if key presents in trie, else false
    fun search(key: String): Boolean {
        val length = key.length
        var index: Int
        var pCrawl = root
        var level: Int = 0
        while (level < length) {
            index = key[level].code - 'a'.code
            if (pCrawl!!.children[index] == null) return false
            pCrawl = pCrawl.children[index]
            level++
        }
        return pCrawl!!.isEndOfWord
    }

    // trie node
    class TrieNode {
        var children = arrayOfNulls<TrieNode>(ALPHABET_SIZE)

        // isEndOfWord is true if the node represents
        // end of a word
        var isEndOfWord = false

        init {
            for (i in 0 until ALPHABET_SIZE) children[i] = null
        }
    }
}

fun main() {
    // Input keys (use only 'a' through 'z' and lower case)
    val keys = arrayOf(
        "the", "a", "there", "answer", "any",
        "by", "bye", "their"
    )
    val output = arrayOf("Not present in trie", "Present in trie")
    val trie = Trie()

    // Construct trie
    var i: Int = 0
    while (i < keys.size) {
        trie.insert(keys[i])
        i++
    }

    // Search for different keys
    if (trie.search("the")) println("the --- " + output[1]) else println("the --- " + output[0])
    if (trie.search("these")) println("these --- " + output[1]) else println("these --- " + output[0])
    if (trie.search("their")) println("their --- " + output[1]) else println("their --- " + output[0])
    if (trie.search("thaw")) println("thaw --- " + output[1]) else println("thaw --- " + output[0])
}