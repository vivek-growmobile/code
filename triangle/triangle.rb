#!/usr/bin/env ruby

## Run with command: ruby triangle.rb [triangle text file] ##

idx = nil
total = 0

triangle = []

File.open(ARGV[0]) do |f|
  f.lines.each do |line|
    line_arr = line.split(" ").map{|s| s.to_i}
    triangle.insert(0, line_arr)
  end
end

triangle.each_with_index do |curr_row, row_num=0|
  unless curr_row.count == 1
    next_row = triangle[row_num + 1]
    next_row.each_with_index do |elem, idx=0|
      next_row[idx] += curr_row[idx + 1] > curr_row[idx] ? curr_row[idx + 1] : curr_row[idx]
    end
  else
    puts curr_row.first
  end
end
