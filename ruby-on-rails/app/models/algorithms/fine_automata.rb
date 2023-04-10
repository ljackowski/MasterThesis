class FineAutomata
  @no_of_chars = 256

  def self.get_next_state(pat, m, state, x)
    if state < m && x == pat[state].ord
      return state + 1
    end
    ns = state
    while ns > 0
      if pat[ns - 1].ord == x
        i = 0
        while i < ns - 1
          if pat[i] != pat[state - ns + 1 + i]
            break
          end
          i += 1
        end
        if i == ns - 1
          return ns
        end
      end
      ns -= 1
    end
    return 0
  end

  def self.compute_tf(pat, m, tf)
    state = 0
    while state <= m
      x = 0
      while x < @no_of_chars
        tf[state][x] = self.get_next_state(pat, m, state, x)
        x += 1
      end
      state += 1
    end
  end

  def self.search(pat, txt)
    _m = pat.length
    _n = txt.length
    _tf = Array.new(_m + 1) { Array.new(@no_of_chars) }
    self.compute_tf(pat, _m, _tf)
    state = 0
    i = 0
    while i < _n
      state = _tf[state][txt[i].ord]
      if state == _m
        print("Pattern found at index " + (i - _m + 1).to_s, "\n")
      end
      i += 1
    end
  end

  def self.main
    pat = "AABAACAADAABAAABAA".chars
    txt = "AABA".chars
    self.search(txt, pat)
  end

end

FineAutomata.main