class BadCharBoyer
    @no_of_chars = 256
    def self.max( a,  b)
      return (a > b) ? a : b
    end
    def self.bad_char_heuristic(str, size, bad_char)
      i = 0
      while i < @no_of_chars
        bad_char[i] = -1
        i += 1
      end
      i = 0
      while i < size
        bad_char[str[i].ord] = i
        i += 1
      end
      bad_char
    end
    def self.search( txt,  pat)
      m = pat.length
      n = txt.length
      bad_char = self.bad_char_heuristic(pat, m, Array.new(@no_of_chars))
      s = 0
      while s <= (n - m)
        j = m - 1
        while j >= 0 && pat[j] == txt[s + j]
          j -= 1
        end
        if j < 0
          print("Patterns occur at shift = " + s.to_s,"\n")
          s += (s + m < n) ? m - bad_char[txt[s + m].ord] : 1
        else
          s += self.max(1, j - bad_char[txt[s + j].ord])
        end
      end
    end

    def self.main
      txt = "ABAAABCD".chars
      pat = "ABC".chars
      self.search(txt, pat)
    end

end

BadCharBoyer.main