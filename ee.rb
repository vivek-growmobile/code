#!/bin/ruby

class PrefNode
    attr_accessor :children, :value, :level

    def initialize(v,l)
        @value = v
        @level = l
        @children = []
    end
    
    def contains(v)
        children.each do |child|
            if child.value == v 
                return child 
            end
        end
        return nil;
    end
end

def  longest_chain( w) 
    w.sort! {|word1, word2| word1.length <=> word2.length}
    #w.map! {|word| word.chars.sort.join}
    w.each {|word| puts word}
    prefixRoot = PrefNode.new(-1,0);
    w.each do |word|
        currNode = prefixRoot
        word.chars.each do |c|
            nextNode = currNode.contains(c)
            if (nextNode)
                currNode = nextNode
            else
                if (currNode.level + 1) == word.length
                    currNode.children.push(PrefNode.new(c, currNode.level + 1))
                end
                break
            end
        end
    end
    #puts prefixRoot.children.last.value
    #prefixRoot.children.each do |child|
        #puts child.value 
    #end
    
    w.sort! {|word1, word2| word2.length <=> word1.length}
    w.each do |word|
        currNode = prefixRoot
        idx = 0
        branches = true
        while word.length > 0 do
            nextNode = currNode.contains(word[idx])
            if nextNode
                currNode = nextNode
                word = word.tr(c,'')
                idx = 0
            else
                if idx == word.length
                    branches = false
                    break
                else
                    idx = idx + 1
                end
            end 
        end
        return word.length if branches
    end
    return 0
end