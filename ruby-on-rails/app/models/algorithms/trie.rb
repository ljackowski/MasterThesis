class TrieNode
  # Define the accessor and reader of class TrieNode
  attr_accessor :children, :isEndOfWord, :alphabet_size

  def initialize(alphabet_size)
    self.isEndOfWord = false
    i = 0
    self.children = []
    while i < alphabet_size
      self.children.push(nil)
      i += 1
    end
  end
end

class Trie

  def initialize(alphabet_size)
    @alphabet_size = alphabet_size
    @root = TrieNode.new(alphabet_size)
  end

  def insert(key)
    length = key.length
    p_crawl = @root
    level = 0
    while level < length
      index = key[level].ord - 'a'.ord
      if p_crawl.children[index] == nil
        p_crawl.children[index] = TrieNode.new(@alphabet_size)
      end
      p_crawl = p_crawl.children[index]
      level += 1
    end
    p_crawl.isEndOfWord = true
  end

  def search(key)
    length = key.length
    p_crawl = @root
    level = 0
    while level < length
      index = key[level].ord - 'a'.ord
      if p_crawl.children[index] == nil
        return false
      end
      p_crawl = p_crawl.children[index]
      level += 1
    end
    (p_crawl.isEndOfWord)
  end

=begin
  def main
    keys = ["the", "a", "there", "answer", "any", "by", "bye", "their"]
    output = ["Not present in trie", "Present in trie"]

    root = TrieNode.new(26)
    trie = Trie.new(26, root)
    i = 0
    while i < keys.length
      trie.insert(keys[i])
      i += 1
    end
    if self.search("the") == true
      print("the --- " + output[1],"\n")
    else
      print("the --- " + output[0],"\n")
    end
    if self.search("these") == true
      print("these --- " + output[1],"\n")
    else
      print("these --- " + output[0],"\n")
    end
    if self.search("their") == true
      print("their --- " + output[1],"\n")
    else
      print("their --- " + output[0],"\n")
    end
    if self.search("thaw") == true
      print("thaw --- " + output[1],"\n")
    else
      print("thaw --- " + output[0],"\n")
    end
  end
=end
end