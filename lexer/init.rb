#!/usr/bin/ruby
# get the results and put them in a hash

f = File.open('./lexer/scan_output.xml', 'r')
network_files = Nokogiri::XML(f)
f.close

# f = File.open('gui_output', 'r')
# gui_files = Nokogiri::XML(f)
# f.close
