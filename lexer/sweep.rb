#!/usr/bin/ruby
# parse every file in the source directory

dir = File.dirname(__FILE__)
require "#{dir}/network_parser.rb"

file_in = ARGV[0] # get filename from command line arg
f = File.open('./lexer/scan_output.xml', 'a')
f.puts("<file path=\"#{file_in}\">")

network_parser = NetworkParser.new(file_in, f)
network_parser.start(file_in) # find files to parse

f.puts("</file>\n\n")
f.close
